package com.example.obrastats.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioObra(onSubmit: (Obra) -> Unit, onNavigateBack: () -> Unit) {
    val nomeState = remember { mutableStateOf("") }
    val clienteState = remember { mutableStateOf<Cliente?>(null) }
    val cidadeState = remember { mutableStateOf("") }
    val enderecoState = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Cadastro de Obra", style = TextStyle(fontSize = 20.sp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nomeState.value,
            onValueChange = { value -> nomeState.value = value },
            label = { Text("Nome da Obra") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = cidadeState.value,
            onValueChange = { value -> cidadeState.value = value },
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = enderecoState.value,
            onValueChange = { value -> enderecoState.value = value },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onSubmit(
                    Obra(
                        null,
                        nomeState.value,
                        clienteState.value ?: Cliente(null, "", "", "", "", "", ""),
                        cidadeState.value,
                        enderecoState.value
                    )
                )
                onNavigateBack();
            },
            enabled = nomeState.value.isNotBlank() && cidadeState.value.isNotBlank() && enderecoState.value.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ObraFormularioPreview() {
    FormularioObra({}, {})
}