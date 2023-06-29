package com.example.obrastats.viewmodel

import android.util.Log
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra
import com.example.obrastats.classes.Servico
import com.example.obrastats.enums.SituacaoServicoEnum
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ServicosViewModel {
    private val db = FirebaseFirestore.getInstance()
    private val _servicos = MutableStateFlow<MutableList<Servico>>(mutableListOf())
    val servicos: StateFlow<MutableList<Servico>> = _servicos

    private var selectedId: String? = null

    fun setSelectedId(id: String?) {
        this.selectedId = id
    }

    fun getServicoSelecionado(): Servico? {
        return servicos.value.find { it.id == selectedId }
    }

    suspend fun getServicos(): StateFlow<MutableList<Servico>> {
        try {
            val listaServicos: MutableList<Servico> = mutableListOf()
            val querySnapshot = db.collection("servico").get().await()
            for (document in querySnapshot) {
                val servicoData = document.data
                val obraData = servicoData["obra"] as HashMap<*, *>
                val clienteData = obraData["cliente"] as HashMap<*, *>

                val cliente = Cliente(
                    id = "1111",
                    nome = clienteData["nome"] as String,
                    sexo = clienteData["sexo"] as String,
                    celular = clienteData["celular"] as String,
                    email = clienteData["email"] as String,
                    cidade = clienteData["cidade"] as String,
                    endereco = clienteData["endereco"] as String,
                    cpfCnpj = clienteData["cpfCnpj"] as String?
                )

                val obra = Obra(
                    id = "1111",
                    nome = obraData["nome"] as String,
                    cliente = cliente,
                    cidade = obraData["cidade"] as String,
                    endereco = obraData["endereco"] as String
                )

                val servico = Servico(
                    id = "1111",
                    descricao = servicoData["descricao"] as String,
                    obra = obra,
                    valorEstimado = (obraData["valorEstimado"] as Number).toDouble(),
                    situacaoServico = obraData["situacaoServico"] as SituacaoServicoEnum
                    //                dataInicio = obraData["dataInicio"] as LocalDate,

                )
                listaServicos.add(servico)
            }
            _servicos.value = listaServicos
        } catch (e: Exception) {
            Log.i("ruim", e.toString())
            // Trate a exceção apropriadamente
        }

        return servicos
    }


    fun salvarServico(servico: Servico) {
        Log.i("salvarrServico", servico.toString())
        val originalId = servico.id
        if (servico.id == null) {
            val uuid = UUID.randomUUID().toString();
            servico.id = uuid
        }
        Log.i("salvarServico", servico.toString())

        val servicoMap = hashMapOf(
            "id" to servico.id,
            "descricao" to servico.descricao,
            "obra" to hashMapOf(
                "id" to servico.obra.id,
                "nome" to servico.obra.nome,
                "cliente" to hashMapOf(
                    "id" to servico.obra.cliente.id,
                    "nome" to servico.obra.cliente.nome,
                    "sexo" to servico.obra.cliente.sexo,
                    "celular" to servico.obra.cliente.celular,
                    "email" to servico.obra.cliente.email,
                    "cidade" to servico.obra.cliente.cidade,
                    "endereco" to servico.obra.cliente.endereco,
                    "cpfCnpj" to servico.obra.cliente.cpfCnpj
                ),
                "cidade" to servico.obra.cidade,
                "endereco" to servico.obra.endereco
            ),
            "valorEstimado" to servico.valorEstimado,
            "situacaoServico" to servico.situacaoServico
        )
        //            "dataInicio" to servico.dataInicio,

        Log.i("servico", "Chamou o banco de dados -> " + servicoMap.toString())

//        db.collection("servico").add(servicoMap)
//            .addOnCompleteListener {
//                Log.i("sucess", "Servico criada com sucesso")
//            }.addOnFailureListener { e ->
//                Log.i("erro", "Erro ao salvar o Servico: $e")
//            }

        db.collection("servico").document(servico.id as String).set(servicoMap)
            .addOnCompleteListener {
                if (originalId != null) {
                    Log.i("sucess", "Servico atualizado com sucesso")
                } else {
                    Log.i("sucess", "Servico criado com sucesso")
                }
            }.addOnFailureListener { e ->
                if (originalId != null) {
                    Log.i("crash", "Erro ao atualizar o servico: $e")
                } else {
                    Log.i("crash", "Erro ao cadastrar o servico: $e")
                }
            }
    }

    fun deletarServico(id: String) {
        db.collection("servico")
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.i("sucess", "Servico deletado com sucesso")
            }
            .addOnFailureListener { e ->
                Log.i("erro", "Erro ao deletar o servico: $e")
            }
    }
}
