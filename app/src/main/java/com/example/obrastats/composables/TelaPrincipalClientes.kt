package com.example.obrastats.composables

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.obrastats.viewmodel.ClientesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipalClientes(navController: NavController, clientesViewModel: ClientesViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Clientes") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

            ) {
                ListaClientes(navController, clientesViewModel)

            }


        },
    floatingActionButton = {
        FloatingActionButton(
            onClick = {
                clientesViewModel.changeIndex(null);
                navController.navigate("criar-editar-cliente")},
            modifier = Modifier
                .padding(16.dp),

        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 6.dp)) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
                Text("Adicionar")
            }
        }
    }
        )

}

@Preview
@Composable
fun TelaPrincipalClientesPreview() {
    val navController = rememberNavController()

    TelaPrincipalClientes(navController, ClientesViewModel() )
}
