package com.example.obrastats.composables.Obra

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obrastats.classes.Obra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObraCard(obra: Obra, onEditClicked: () -> Unit) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = obra.nome,
                    fontSize = 20.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onEditClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar obra"
                    )
                }
            }
            Text(text = "Cliente: ${obra.cliente.nome}")
            Text(text = "Cidade: ${obra.cidade}")
            Text(text = "Endereço: ${obra.endereco}")
        }
    }
}

//@Preview
//@Composable
//fun ObraCardPreview() {
//    ObraCard(
//        Obra(
//            null,
//            "Obra 1",
//            Cliente(
//                null,
//                "Cliente 1",
//                "Feminino",
//                "34999999999",
//                "cliente1@email.com",
//                "Uberaba",
//                "Rua A"
//            ),
//            "Uberaba",
//            "Rua B"
//        )
//    )
//}
