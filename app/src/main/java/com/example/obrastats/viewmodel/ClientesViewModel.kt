package com.example.obrastats.viewmodel

import android.util.Log
import com.example.obrastats.classes.Cliente
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ClientesViewModel {
    private var selectedId: String? = null
    private var db = FirebaseFirestore.getInstance()
    private val _clientes = MutableStateFlow<MutableList<Cliente>>(mutableListOf())
    val clientes: StateFlow<MutableList<Cliente>> = _clientes;
    fun setSelectedId(id: String?) {
        this.selectedId = id
    }

    fun getClienteSelecionado(): Cliente? {
        return clientes.value.find { it.id == selectedId }
    }

    suspend fun getClientes(): Flow<MutableList<Cliente>> {
        val listaClientes: MutableList<Cliente> = mutableListOf()
        db.collection("cliente")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val cliente = document.data
                    Log.i("cliente", document.data.toString())
                    Log.i("docId", document.id)

                    listaClientes.add(
                        Cliente(
                            id = document.id,
                            nome = cliente["nome"] as String,
                            sexo = cliente["sexo"] as String,
                            celular = cliente["celular"] as String,
                            email = cliente["email"] as String,
                            cidade = cliente["cidade"] as String,
                            endereco = cliente["endereco"] as String
                        )
                    )
                }
                _clientes.value = listaClientes
            }

        return _clientes
    }

    fun salvarCliente(cliente: Cliente) {
        val clientMap = hashMapOf(
            "nome" to cliente.nome,
            "sexo" to cliente.sexo,
            "celular" to cliente.celular,
            "email" to cliente.email,
            "cidade" to cliente.cidade,
            "endereco" to cliente.endereco
        )

        if (cliente.id != null) {
            db.collection("cliente").document(cliente.id).set(cliente)
                .addOnCompleteListener {

                }
        } else {
            db.collection("cliente").document().set(cliente)
                .addOnCompleteListener {
                }
        }
    }
}