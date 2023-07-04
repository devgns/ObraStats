package com.example.obrastats.composables.Colaborador

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.viewmodel.ColaboradoresViewModel

@Composable
fun ListaColaboradores(navController: NavController, colaboradores: MutableList<Colaborador>,  colaboradoresVM: ColaboradoresViewModel) {
    Log.d("ListaColaboradores", colaboradores.toString())

    if (colaboradores.isEmpty()) {
        Text(text = "Não há colaboradores para mostrar")
    } else {
        LazyColumn {
            itemsIndexed(colaboradores) {index, colaborador ->
                ColaboradorCard(colaborador = colaborador, onEditClicked = {
                    colaboradoresVM.setSelectedId(colaborador.id);
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
