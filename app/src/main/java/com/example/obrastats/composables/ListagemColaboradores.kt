package com.example.obrastats.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.obrastats.viewmodel.ColaboradoresViewModel

@Composable
fun ListaColaboradores(navController: NavController, colaboradoresViewModel: ColaboradoresViewModel) {
    val colaboradores = colaboradoresViewModel.getColaboradoresList();
    if (colaboradores.isEmpty()) {
        Text(text = "Não há colaboradores para mostrar")
    } else {
        LazyColumn {
            itemsIndexed(colaboradores) {index, colaborador ->
                ColaboradorCard(colaborador = colaborador, onEditClicked = {
                    colaboradoresViewModel.changeIndex(index);
                    navController.navigate("criar-editar-colaborador")
                })
            }
        }
    }
}

//@Preview
//@Composable
//fun ListaColaboradoresPreview() {
//
//    )
//
//    Column {
//        ListaColaboradores(colaboradores = colaboradores)
//    }
//}

//@Preview
//@Composable
//fun ListaColaboradoresPreview() {
//    val colaboradores = listOf<Colaborador>()
//
//    Column {
//        ListaColaboradores(colaboradores = colaboradores)
//    }
//}
