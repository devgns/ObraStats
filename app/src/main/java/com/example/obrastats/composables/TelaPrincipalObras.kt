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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.obrastats.MainScreen
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipalObras() {
    var showDialog by remember { mutableStateOf(false) }
    val obras = listOf(
        Obra(
            id = 1,
            nome = "Obra 1",
            cliente = Cliente(
                id = 1,
                nome = "Cliente 1",
                sexo = "Feminino",
                celular = "34999999999",
                email = "cliente1@example.com",
                cidade = "Uberaba",
                endereco = "Rua A"
            ),
            cidade = "Uberaba",
            endereco = "Rua X"
        ),
        Obra(
            id = 2,
            nome = "Obra 2",
            cliente = Cliente(
                id = 2,
                nome = "Cliente 2",
                sexo = "Masculino",
                celular = "34888888888",
                email = "cliente2@example.com",
                cidade = "Uberlândia",
                endereco = "Rua B"
            ),
            cidade = "Uberlândia",
            endereco = "Rua Y"
        ),
        Obra(
            id = 3,
            nome = "Obra 3",
            cliente = Cliente(
                id = 3,
                nome = "Cliente 3",
                sexo = "Feminino",
                celular = "34777777777",
                email = "cliente3@example.com",
                cidade = "Belo Horizonte",
                endereco = "Rua Z"
            ),
            cidade = "Belo Horizonte",
            endereco = "Rua Z"
        )
    )


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Obras") },
                navigationIcon = {
                    IconButton(onClick = { /* Navegar para a tela anterior */ }) {
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
                ListaObras(obras = obras)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
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

    if (showDialog) {
        // Exibir diálogo ou navegar para a tela de preenchimento de formulário
    }
}



@Preview
@Composable
fun TelaPrincipalObrasPreview() {
    TelaPrincipalObras()
}

