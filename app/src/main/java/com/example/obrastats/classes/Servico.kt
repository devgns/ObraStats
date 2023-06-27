package com.example.obrastats.classes
import com.example.obrastats.enums.SituacaoServicoEnum
import java.util.Date

class Servico(
    val id: String?,
    val descricao: String,
    val obra: Obra,
    val valorEstimado: Double?,
    val dataInicio: Date,
    val situacaoServico: SituacaoServicoEnum
) {

}