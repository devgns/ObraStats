package com.example.obrastats.viewmodel

import com.example.obrastats.classes.Colaborador
import com.example.obrastats.enums.ModeloDeContratacaoEnum

class ColaboradoresViewModel {
    private var currentIndex: Int? = null

    val colaboradores: MutableList<Colaborador> = mutableListOf(
        Colaborador(
            id = 1,
            nome = "Colaborador 1",
            profissao = "Engenheiro",
            modeloDeContrato = ModeloDeContratacaoEnum.DIARISTA,
            sexo = "Masculino",
            celular = "34999999999",
            email = "colaborador1@example.com",
            cidade = "Uberaba",
            endereco = "Rua A"
        ),
        Colaborador(
            id = 2,
            nome = "Colaborador 2",
            profissao = "Arquiteto",
            modeloDeContrato = ModeloDeContratacaoEnum.EFETIVO,
            sexo = "Feminino",
            celular = "34888888888",
            email = "colaborador2@example.com",
            cidade = "Uberlândia",
            endereco = "Rua B"
        ),
        Colaborador(
            id = 3,
            nome = "Colaborador 3",
            profissao = "Pedreiro",
            modeloDeContrato = ModeloDeContratacaoEnum.DIARISTA,
            sexo = "Masculino",
            celular = "34777777777",
            email = "colaborador3@example.com",
            cidade = "Uberlândia",
            endereco = "Rua C"
        )
    )

    fun getColaboradoresList(): List<Colaborador> {
        return colaboradores
    }

    fun addColaborador(colaborador: Colaborador) {
        colaboradores.add(colaborador)
    }

    fun getCurrentIndex(): Int? {
        return currentIndex
    }

    fun changeIndex(newIndex: Int?) {
        if (newIndex == null) {
            currentIndex = newIndex
        } else if (newIndex >= 0 && newIndex < colaboradores.size) {
            currentIndex = newIndex
        }
    }

    fun updateColaboradorAtIndex(index: Int, novoColaborador: Colaborador) {
        if (index >= 0 && index < colaboradores.size) {
            colaboradores[index] = novoColaborador
        }
    }
}
