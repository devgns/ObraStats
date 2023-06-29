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
import java.time.LocalDate
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
        val listaServicos: MutableList<Servico> = mutableListOf()
        db.collection("servico").get().await().forEach { document ->
            val servicoData = document.data
            val obraData = servicoData["obra"] as HashMap<*, *>
            val clienteData = obraData["cliente"] as HashMap<*, *>

            val cliente = Cliente(
                id = null,
                nome = clienteData["nome"] as String,
                sexo = clienteData["sexo"] as String,
                celular = clienteData["celular"] as String,
                email = clienteData["email"] as String,
                cidade = clienteData["cidade"] as String,
                endereco = clienteData["endereco"] as String,
                cpfCnpj = clienteData["cpfCnpj"] as String?
            )

            val obra = Obra(
                id = null,
                nome = obraData["nome"] as String,
                cliente = cliente,
                cidade = obraData["cidade"] as String,
                endereco = obraData["endereco"] as String
            )
            val servico = Servico(
                id = null,
                descricao = servicoData["descricao"] as String,
                obra = obra,
                valorEstimado = obraData["valorEstimado"] as Double,
                dataInicio = obraData["dataInicio"] as LocalDate,
                situacaoServico = obraData["situacaoServico"] as SituacaoServicoEnum
            )
            listaServicos.add(servico)
        }
        _servicos.value = listaServicos
        return servicos
    }


    fun salvarServico(servico: Servico) {
        val originalId = servico.id
        if (servico.id == null) {
            val uuid = UUID.randomUUID().toString();
            servico.id = uuid
        }
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
            "dataInicio" to servico.dataInicio,
            "situacaoServico" to servico.situacaoServico
        )

        db.collection("servico").document(servico.id as String).set(servicoMap)
            .addOnCompleteListener {
                if (originalId != null) {
                    Log.i("sucess", "Servico atualizado com sucesso")
                } else {
                    Log.i("sucess", "Servico criado com sucesso")
                }
            }.addOnFailureListener { e ->
                if (originalId != null) {
                    Log.i("erro", "Erro ao atualizar o servico: $e")
                } else {
                    Log.i("erro", "Erro ao cadastrar o servico: $e")
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
