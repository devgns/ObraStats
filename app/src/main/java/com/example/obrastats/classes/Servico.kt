package com.example.obrastats.classes
import com.example.obrastats.enums.SituacaoServicoEnum

data class Servico(
    var id: String?,
    val descricao: String,
    val obra: Obra,
    val valorEstimado: Double?,
    val situacaoServico: SituacaoServicoEnum
) {
}
//    val dataInicio: LocalDate,
