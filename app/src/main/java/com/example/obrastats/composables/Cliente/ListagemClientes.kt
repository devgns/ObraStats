package com.example.obrastats.composables.Cliente

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.obrastats.classes.Cliente
import com.example.obrastats.viewmodel.ClientesViewModel

@Composable
fun ListaClientes(navController: NavController,clientes: MutableList<Cliente> , clientesViewModel: ClientesViewModel) {
    if (clientes.isEmpty()) {
        Text(text = "Não há clientes para mostrar")
    } else {
        LazyColumn {
            itemsIndexed(clientes) { index, cliente ->
                ClienteCard(cliente = cliente, onEditClicked = {
                    clientesViewModel.setSelectedId(cliente.id);
                    navController.navigate("criar-editar-cliente");
                })
            }
        }
    }

}

//@Preview
//@Composable
//fun ListaClientesPreview() {
//    val clientes = listOf(
//        Cliente(
//            id = 1,
//            nome = "Cliente 1",
//            sexo = "Feminino",
//            celular = "34999999999",
//            email = "cliente1@email.com",
//            cidade = "Uberaba",
//            endereco = "Rua A"
//        ),
//        Cliente(
//            id = 2,
//            nome = "Cliente 2",
//            sexo = "Masculino",
//            celular = "34888888888",
//            email = "cliente2@email.com",
//            cidade = "Uberlândia",
//            endereco = "Rua B"
//        )
//    )
//
//    Column {
//        ListaClientes(clientes = clientes)
//    }
//}
