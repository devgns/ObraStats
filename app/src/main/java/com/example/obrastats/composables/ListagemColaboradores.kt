package com.example.obrastats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.enums.ModeloDeContratacaoEnum

@Composable
fun ListaColaboradores(colaboradores: List<Colaborador>) {
    if (colaboradores.isEmpty()) {
        Text(text = "Não há colaboradores para mostrar")
    } else {
        LazyColumn {
            items(colaboradores) { colaborador ->
                ColaboradorCard(colaborador = colaborador)
            }
        }
    }
}

@Preview
@Composable
fun ListaColaboradoresPreview() {
    val colaboradores = listOf(
        Colaborador(
            id = 1,
            nome = "Colaborador 1",
            profissao = "Engenheiro",
            modeloDeContrato = ModeloDeContratacaoEnum.DIARISTA,
            sexo = "Masculino",
            celular = "34999999999",
            email = "colaborador1@email.com",
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
            email = "colaborador2@email.com",
            cidade = "Uberlândia",
            endereco = "Rua B"
        )
    )

    Column {
        ListaColaboradores(colaboradores = colaboradores)
    }
}

//@Preview
//@Composable
//fun ListaColaboradoresPreview() {
//    val colaboradores = listOf<Colaborador>()
//
//    Column {
//        ListaColaboradores(colaboradores = colaboradores)
//    }
//}
