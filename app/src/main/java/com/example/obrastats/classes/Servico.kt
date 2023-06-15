package com.example.obrastats.classes
import com.example.obrastats.enums.SituacaoServicoEnum
import java.util.Date

class Servico(
    val id: Int?,
    val obra: Obra,
    val valorPrevisto: Double?,
    val dataInicio: Date,
    val situacaoServico: SituacaoServicoEnum
) {

}