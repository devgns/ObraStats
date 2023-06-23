package com.example.obrastats.viewmodel

import android.util.Log
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.enums.ModeloDeContratacaoEnum
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColaboradoresViewModel {
    private var currentIndex: Int? = null
    private val db = FirebaseFirestore.getInstance()
    private val _colaboradores = MutableStateFlow<MutableList<Colaborador>>(mutableListOf())
    val colaboradores: StateFlow<MutableList<Colaborador>> = _colaboradores;

    private var selectedId: String? = null

    fun setSelectedId(id: String?) {
        this.selectedId = id;
    }

    fun getColaboradorSelecionado(): Colaborador? {
        return colaboradores.value.find { it.id == selectedId }
    }

    fun getColaboradoresList(): List<Colaborador> {
        return mutableListOf()
//        return listaColaboradores
    }

    fun addColaborador(colaborador: Colaborador) {
//        listaColaboradores.add(colaborador)
    }

    fun getCurrentIndex(): Int? {
        return currentIndex
    }

    fun changeIndex(newIndex: Int?) {

    }

    fun updateColaboradorAtIndex(index: Int, novoColaborador: Colaborador) {

    }

    suspend fun getColaboradores(): Flow<MutableList<Colaborador>> {
        val listaColaboradores: MutableList<Colaborador> = mutableListOf()
        db.collection("colaborador").get().addOnSuccessListener { result ->
            for (document in result) {
                val colaborador = document.data
                listaColaboradores.add(
                    Colaborador(
                        id = document.id,
                        nome = colaborador["nome"] as String,
                        profissao = colaborador["profissao"] as String,
                        modeloDeContrato =
                        ModeloDeContratacaoEnum.valueOf(colaborador["modeloDeContrato"] as String),
                        sexo = colaborador["sexo"] as String,
                        celular = colaborador["celular"] as String,
                        email = colaborador["email"] as String,
                        cidade = colaborador["cidade"] as String,
                        endereco = colaborador["endereco"] as String
                    )
                );
                _colaboradores.value = listaColaboradores;
            }
        }
        return colaboradores;
    }


    fun salvarColaborador(colaborador: Colaborador) {

        val colaboradorMap = hashMapOf(
            "nome" to colaborador.nome,
            "profissao" to colaborador.profissao,
            "modeloDeContrato" to colaborador.modeloDeContrato,
            "sexo" to colaborador.sexo,
            "celular" to colaborador.celular,
            "email" to colaborador.email,
            "cidade" to colaborador.cidade,
            "endereco" to colaborador.endereco
        )
        if (colaborador.id != null) {
            db.collection("colaborador").document(colaborador.id).set(colaboradorMap)
                .addOnCompleteListener {

                }
        } else {
            db.collection("colaborador").document().set(colaboradorMap)
                .addOnCompleteListener {
                }
        }
    }
}
