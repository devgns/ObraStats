package com.example.obrastats.classes
import com.example.obrastats.enums.SituacaoServicoEnum
import java.time.LocalDate

class Servico(
    var id: String?,
    val descricao: String,
    val obra: Obra,
    val valorEstimado: Double?,
    val dataInicio: LocalDate,
    val situacaoServico: SituacaoServicoEnum
) {

}