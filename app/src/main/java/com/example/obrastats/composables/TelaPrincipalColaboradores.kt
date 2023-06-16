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
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.classes.Obra
import com.example.obrastats.enums.ModeloDeContratacaoEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipalColaboradores() {
    var showDialog by remember { mutableStateOf(false) }
    val colaboradores = listOf(
        Colaborador(
            id = 1,
            nome = "Colaborador 1",
            profissao = "Pedreiro",
            modeloDeContrato = ModeloDeContratacaoEnum.EFETIVO,
            sexo = "Masculino",
            celular = "34999999999",
            email = "colaborador1@example.com",
            cidade = "Uberaba",
            endereco = "Rua A"
        ),
        Colaborador(
            id = 2,
            nome = "Colaborador 2",
            profissao = "Eletricista",
            modeloDeContrato = ModeloDeContratacaoEnum.DIARISTA,
            sexo = "Masculino",
            celular = "34888888888",
            email = "colaborador2@example.com",
            cidade = "Uberlândia",
            endereco = "Rua B"
        ),
        Colaborador(
            id = 3,
            nome = "Colaborador 3",
            profissao = "Encanador",
            modeloDeContrato = ModeloDeContratacaoEnum.DIARISTA,
            sexo = "Masculino",
            celular = "34777777777",
            email = "colaborador3@example.com",
            cidade = "Belo Horizonte",
            endereco = "Rua C"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Colaboradores") },
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
                ListaColaboradores(colaboradores = colaboradores)
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
fun TelaPrincipalColaboradoresPreview() {
    TelaPrincipalColaboradores()
}
