package com.example.obrastats.composables.Colaborador

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.obrastats.composables.Colaborador.ListaColaboradores
import com.example.obrastats.viewmodel.ColaboradoresViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPrincipalColaboradores(navController: NavController,  colaboradoresVM: ColaboradoresViewModel) {

    val colaboradoresState = colaboradoresVM.colaboradores.collectAsState(initial = mutableListOf())
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            colaboradoresVM.getColaboradores()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Colaboradores") },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate("home") }) {
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
                ListaColaboradores(navController, colaboradoresState.value, colaboradoresVM)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    colaboradoresVM.setSelectedId(null);
                    navController.navigate("criar-editar-colaborador") },
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

//@Preview
//@Composable
//fun TelaPrincipalColaboradoresPreview() {
//    TelaPrincipalColaboradores()
//}
