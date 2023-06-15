package com.example.obrastats.composables

import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview


import com.example.obrastats.classes.Cliente


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteCard(cliente: Cliente) {
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
                    text = cliente.nome,
                    fontSize = 20.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /* Navegar para a tela de edição do cliente */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar cliente"
                    )
                }
            }
            Text(text = "Sexo: ${cliente.sexo}")
            Text(text = "Celular: ${cliente.celular}")
            Text(text = "Email: ${cliente.email}")
            Text(text = "Cidade: ${cliente.cidade}")
            Text(text = "Endereço: ${cliente.endereco}")
        }
    }
}

@Preview
@Composable
fun ClienteCardPreview(){
    ClienteCard(Cliente(null, "Gustavo", "Masculino", "34999999999", "gustavo@email.com", "Uberaba", "Rua Feliz"))
}
