package com.example.obrastats.viewmodel

import android.util.Log
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.enums.ModeloDeContratacaoEnum
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class ColaboradoresViewModel {
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

    suspend fun getColaboradores(): Flow<MutableList<Colaborador>> {
        val listaColaboradores: MutableList<Colaborador> = mutableListOf()
        db.collection("colaborador").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.i("document", document.toString())
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
                        endereco = colaborador["endereco"] as String,
                        cpfCnpj = colaborador["cpfCnpj"] as String?
                    )
                );
                _colaboradores.value = listaColaboradores;
            }
        }
        Log.i("saiu","saiu")
        return colaboradores;
    }


    fun salvarColaborador(colaborador: Colaborador) {
        val originalId = colaborador.id
        if (colaborador.id == null) {
            val uuid = UUID.randomUUID().toString();
            colaborador.id = uuid
        }

        val colaboradorMap = hashMapOf(
            "id" to colaborador.id,
            "nome" to colaborador.nome,
            "profissao" to colaborador.profissao,
            "modeloDeContrato" to colaborador.modeloDeContrato,
            "sexo" to colaborador.sexo,
            "celular" to colaborador.celular,
            "email" to colaborador.email,
            "cidade" to colaborador.cidade,
            "endereco" to colaborador.endereco,
            "cpfCnpj" to colaborador.cpfCnpj
        )


        db.collection("colaborador").document(colaborador.id as String).set(colaboradorMap)
            .addOnCompleteListener {
                if (originalId != null) {
                    Log.i("sucess", "Colaborador atualizado com sucesso")
                } else {
                    Log.i("sucess", "Colaborador criado com sucesso")
                }
            }.addOnFailureListener { e ->
                if (originalId != null) {
                    Log.i("erro", "Erro ao atualizar Colaborador: $e")
                } else {
                    Log.i("erro", "Erro ao cadastrar Colaborador: $e")
                }
            }
    }
}
