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
        db.collection("obra").get().addOnSuccessListener { result ->
            Log.i("obra",result.toString())

            for (document in result) {
                val obra = document.data
                val cliente = Cliente(
                    id = obra["cliente.id"] as String,
                    nome = obra["cliente.nome"] as String,
                    sexo = obra["cliente.sexo"] as String,
                    celular = obra["cliente.celular"] as String,
                    email = obra["cliente.email"] as String,
                    cidade = obra["cliente.cidade"] as String,
                    endereco = obra["cliente.endereco"] as String
                )
                listaObras.add(
                    Obra(
                        id = document.id,
                        nome = obra["nome"] as String,
                        cliente = cliente,
                        cidade = obra["cidade"] as String,
                        endereco = obra["endereco"] as String
                    )
                )
            }
            _obras.value = listaObras
        }
        return obras
    }

    fun salvarObra(obra: Obra) {
        val obraMap = hashMapOf(
            "nome" to obra.nome,
            "cliente" to obra.cliente,
            "cidade" to obra.cidade,
            "endereco" to obra.endereco
        )
        if (obra.id != null) {
            db.collection("obra").document(obra.id).set(obraMap)
                .addOnCompleteListener {

                }
        } else {
            db.collection("obra").document().set(obraMap)
                .addOnCompleteListener {

                }
        }
    }

}
