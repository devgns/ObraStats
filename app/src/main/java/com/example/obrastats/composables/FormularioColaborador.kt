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
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.enums.ModeloDeContratacaoEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColaboradorFormulario(onSubmit: (Colaborador) -> Unit) {
    val nomeState = remember { mutableStateOf("") }
    val profissaoState = remember { mutableStateOf("") }
    val modeloContratoState = remember { mutableStateOf<ModeloDeContratacaoEnum?>(null) }
    val sexoState = remember { mutableStateOf<String?>(null) }
    val celularState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val cidadeState = remember { mutableStateOf("") }
    val enderecoState = remember { mutableStateOf("") }
    val listaSexos = listOf("Masculino", "Feminino")

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Cadastro de Colaborador", style = TextStyle(fontSize = 20.sp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nomeState.value,
            onValueChange = { value -> nomeState.value = value },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = profissaoState.value,
            onValueChange = { value -> profissaoState.value = value },
            label = { Text("Profissão") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Formulário de Modelo de Contratação
        dropDownForm(
            ModeloDeContratacaoEnum.values().toList(),
            placeHolder = "Modelo de Contratação",
            selectedItem = modeloContratoState,
            itemToString = { it?.descricao ?: "" }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            dropDownForm(
                listaSexos,
                placeHolder = "Sexo",
                selectedItem = sexoState,
                itemToString = { it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = celularState.value,
            onValueChange = { value -> celularState.value = value },
            label = { Text("Celular") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { value -> emailState.value = value },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = cidadeState.value,
            onValueChange = { value -> cidadeState.value = value },
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = enderecoState.value,
            onValueChange = { value -> enderecoState.value = value },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onSubmit(
                    Colaborador(
                        null,
                        nomeState.value,
                        profissaoState.value,
                        modeloContratoState.value ?: ModeloDeContratacaoEnum.DIARISTA,
                        sexoState.value ?: "",
                        celularState.value,
                        emailState.value,
                        cidadeState.value,
                        enderecoState.value
                    )
                )

            },
            enabled = nomeState.value.isNotBlank() && profissaoState.value.isNotBlank() && (sexoState.value?.isNotBlank() == true) && modeloContratoState.value != null && celularState.value.isNotBlank() && emailState.value.isNotBlank() && cidadeState.value.isNotBlank() && enderecoState.value.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ColaboradorFormularioPreview() {
    ColaboradorFormulario({})
}