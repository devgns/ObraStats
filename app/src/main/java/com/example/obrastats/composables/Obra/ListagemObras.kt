package com.example.obrastats.composables.Obra

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.obrastats.classes.Obra
import com.example.obrastats.viewmodel.ObrasViewModel


@Composable
fun ListaObras(navController: NavController, obras: MutableList<Obra> ,obrasVM: ObrasViewModel) {
    Log.d("ListaObras", obras.toString())

    if (obras.isEmpty()) {
        Text(text = "Não há obras para mostrar")
    } else {
        LazyColumn {
            itemsIndexed(obras) { index, obra ->
                ObraCard(obra = obra, onEditClicked = {
                    obrasVM.setSelectedId(obra.id)
                    Log.i("test", "adicionar-obra")
                    navController.navigate("criar-editar-obra")
                })
            }
        }
    }
}

//@Preview
//@Composable
//fun ListaObrasPreview() {
//
//    )
//
//    Column {
//        ListaObras(obras = obras)
//    }
//}
