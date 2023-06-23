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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.obrastats.viewmodel.ClientesViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipalClientes(navController: NavController, clientesVM: ClientesViewModel) {

    val clientesState = clientesVM.clientes.collectAsState(initial = mutableListOf())
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            clientesVM.getClientes()
        }
    }

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
                ListaClientes(navController,clientesState.value ,clientesVM)

            }


        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    clientesVM.setSelectedId(null);
                    navController.navigate("criar-editar-cliente")
                },
                modifier = Modifier
                    .padding(16.dp),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 6.dp)
                ) {
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

    TelaPrincipalClientes(navController, ClientesViewModel())
}
