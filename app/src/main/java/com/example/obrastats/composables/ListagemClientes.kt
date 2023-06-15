package com.example.obrastats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.obrastats.classes.Cliente

@Composable
fun ListaClientes(clientes: List<Cliente>) {
    if (clientes.isEmpty()) {
        Text(text = "Não há clientes para mostrar")
    } else {
        LazyColumn {
            items(clientes) { cliente ->
                ClienteCard(cliente = cliente)
            }
        }
    }
}

@Preview
@Composable
fun ListaClientesPreview() {
    val clientes = listOf(
        Cliente(
            id = 1,
            nome = "Cliente 1",
            sexo = "Feminino",
            celular = "34999999999",
            email = "cliente1@email.com",
            cidade = "Uberaba",
            endereco = "Rua A"
        ),
        Cliente(
            id = 2,
            nome = "Cliente 2",
            sexo = "Masculino",
            celular = "34888888888",
            email = "cliente2@email.com",
            cidade = "Uberlândia",
            endereco = "Rua B"
        )
    )

    Column {
        ListaClientes(clientes = clientes)
    }
}
