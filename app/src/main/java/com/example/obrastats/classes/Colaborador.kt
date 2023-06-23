package com.example.obrastats.classes

import com.example.obrastats.enums.ModeloDeContratacaoEnum

data class Colaborador(
    val id: String?,
    val nome: String,
    val profissao: String,
    val modeloDeContrato: ModeloDeContratacaoEnum,
    val sexo: String,
    val celular: String,
    val email: String,
    val cidade: String,
    val endereco: String
) {
}