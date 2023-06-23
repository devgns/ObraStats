package com.example.obrastats.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.obrastats.classes.Cliente
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class ClientesViewModel {
    private var currentIndex: Int? = null
    private var db = FirebaseFirestore.getInstance()
    private val clientes: MutableList<Cliente> = mutableListOf()


    fun getListaClientes(): List<Cliente> {
        return clientes;
    }

    suspend fun fetchClientes(): List<Cliente> {
//        clientes.clear();
        return withContext(Dispatchers.IO) {
            val clientes = mutableListOf<Cliente>()
            try{
                db.collection("cliente")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            val cliente = document.data
                            Log.i("cliente", document.data.toString())
                            Log.i("docId", document.id)

                            clientes.add(
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
                    }
            } catch (exception: Exception) {

            }
            clientes
        }
    }

    fun addCliente(cliente: Cliente, callback: (Boolean) -> Unit) {
        clientes.add(cliente)

        val clientMap = hashMapOf(
            "nome" to cliente.nome,
            "sexo" to cliente.sexo,
            "celular" to cliente.celular,
            "email" to cliente.email,
            "cidade" to cliente.cidade,
            "endereco" to cliente.endereco
        )

        db.collection("cliente").document().set(clientMap).addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            callback(false)
        }
    }

    fun getCurrentIndex(): Int? {
        return currentIndex
    }

    fun update(cliente: Cliente, callback: (Boolean) -> Unit) {
        if (cliente.id != null) {
            val clientMap = hashMapOf(
                "nome" to cliente.nome,
                "sexo" to cliente.sexo,
                "celular" to cliente.celular,
                "email" to cliente.email,
                "cidade" to cliente.cidade,
                "endereco" to cliente.endereco
            )
            db.collection("cliente").document(cliente.id).set(clientMap).addOnSuccessListener {
                callback(true)
            }.addOnFailureListener {
                callback(false)
            }
        }
    }

    fun changeIndex(newIndex: Int?) {
        if (newIndex == null) {
            currentIndex = newIndex;
        } else if (newIndex >= 0 && newIndex < clientes.size) {
            currentIndex = newIndex
        }
    }

}
