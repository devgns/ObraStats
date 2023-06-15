package com.example.obrastats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra


@Composable
fun ListaObras(obras: List<Obra>) {
    if (obras.isEmpty()) {
        Text(text = "Não há obras para mostrar")
    } else {
        LazyColumn {
            items(obras) { obra ->
                ObraCard(obra = obra)
            }
        }
    }
}

@Preview
@Composable
fun ListaObrasPreview() {
    val obras = listOf(
        Obra(
            id = 1,
            nome = "Obra 1",
            cliente = Cliente(
                id = 1,
                nome = "Cliente 1",
                sexo = "Feminino",
                celular = "34999999999",
                email = "cliente1@email.com",
                cidade = "Uberaba",
                endereco = "Rua A"
            ),
            cidade = "Uberaba",
            endereco = "Rua B"
        ),
        Obra(
            id = 2,
            nome = "Obra 2",
            cliente = Cliente(
                id = 2,
                nome = "Cliente 2",
                sexo = "Masculino",
                celular = "34888888888",
                email = "cliente2@email.com",
                cidade = "Uberlândia",
                endereco = "Rua C"
            ),
            cidade = "Uberlândia",
            endereco = "Rua D"
        )
    )

    Column {
        ListaObras(obras = obras)
    }
}
