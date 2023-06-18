package com.example.obrastats.composables

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.obrastats.viewmodel.ObrasViewModel


@Composable
fun ListaObras(navController: NavController, obrasViewModel: ObrasViewModel) {
    val obras = obrasViewModel.getObrasList();
    if (obras.isEmpty()) {
        Text(text = "Não há obras para mostrar")
    } else {
        LazyColumn {
            itemsIndexed(obras) { index, obra ->
                ObraCard(obra = obra, onEditClicked = {
                    obrasViewModel.changeIndex(index);
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
