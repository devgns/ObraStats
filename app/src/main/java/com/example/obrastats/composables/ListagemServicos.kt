package com.example.obrastats.composables

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.obrastats.classes.Servico
import com.example.obrastats.viewmodel.ServicosViewModel

@Composable
fun ListaServicos(navController: NavController, servicos: MutableList<Servico>, servicosVM: ServicosViewModel) {
    Log.d("ListaServicos", servicos.toString())

    if (servicos.isEmpty()) {
        Text(text = "Não há serviços para mostrar")
    } else {
        LazyColumn {
            itemsIndexed(servicos) { index, servico ->
                ServicoCard(navController,servico = servico, onEditClicked = {
                    servicosVM.setSelectedId(servico.id)
                    Log.i("test", "adicionar-servico")
                    navController.navigate("criar-editar-servico")
                },  onDeleteClicked = {
                    servicosVM.deletarServico(servico.id as String)
                    navController.navigate("lista-servicos")
                    Log.i("test", "deletar-servico")
                })
            }
        }
    }
}