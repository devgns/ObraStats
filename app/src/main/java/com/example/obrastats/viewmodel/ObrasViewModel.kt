package com.example.obrastats.viewmodel

import android.util.Log
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.classes.Obra
import com.example.obrastats.enums.ModeloDeContratacaoEnum
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class ObrasViewModel {
    private val db = FirebaseFirestore.getInstance()
    private val _obras = MutableStateFlow<MutableList<Obra>>(mutableListOf())
    val obras: StateFlow<MutableList<Obra>> = _obras

    private var selectedId: String? = null

    fun setSelectedId(id: String?) {
        this.selectedId = id
    }

    fun getObraSelecionada(): Obra? {
        return obras.value.find { it.id == selectedId }
    }

    suspend fun getObras(): StateFlow<MutableList<Obra>> {
        val listaObras: MutableList<Obra> = mutableListOf()
        db.collection("obra").get().await().forEach { document ->
            val obraData = document.data
            val obra = Obra(
                id =obraData["id"] as String?,
                nome = obraData["nome"] as String,
                cliente = Cliente(
                    id = (obraData["cliente"] as HashMap<*, *>)["id"] as String?,
                    nome = (obraData["cliente"] as HashMap<*, *>)["nome"] as String,
                    sexo = (obraData["cliente"] as HashMap<*, *>)["sexo"] as String,
                    celular = (obraData["cliente"] as HashMap<*, *>)["celular"] as String,
                    email = (obraData["cliente"] as HashMap<*, *>)["email"] as String,
                    cidade = (obraData["cliente"] as HashMap<*, *>)["cidade"] as String,
                    endereco = (obraData["cliente"] as HashMap<*, *>)["endereco"] as String,
                    cpfCnpj = (obraData["cliente"] as HashMap<*, *>)["cpfCnpj"] as String?,
                ),
                cidade = obraData["cidade"] as String,
                endereco = obraData["endereco"] as String
            )
            listaObras.add(obra)
        }
        _obras.value = listaObras
        return obras
    }

    fun salvarObra(obra: Obra) {

        val obraMap = hashMapOf(
            "nome" to obra.nome,
            "cliente" to hashMapOf(
                "id" to obra.cliente.id,
                "nome" to obra.cliente.nome,
                "sexo" to obra.cliente.sexo,
                "celular" to obra.cliente.celular,
                "email" to obra.cliente.email,
                "cidade" to obra.cliente.cidade,
                "endereco" to obra.cliente.endereco
            ),
            "cidade" to obra.cidade,
            "endereco" to obra.endereco
        )
        if (obra.id != null) {
            db.collection("obra").document(obra.id).set(obraMap)
                .addOnCompleteListener {
                    Log.i("sucess", "Obra atualizada com sucesso")
                }.addOnFailureListener { e ->
                    Log.i("erro", "Erro ao atualizar a obra: $e")
                }
        } else {
            db.collection("obra").add(obraMap)
                .addOnCompleteListener {
                    Log.i("sucess", "Obra criada com sucesso")
                }.addOnFailureListener { e ->
                    Log.i("erro", "Erro ao salvar o obra: $e")
                }
        }
    }
}



