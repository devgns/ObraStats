package com.example.obrastats.composables

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.obrastats.classes.Colaborador
import com.example.obrastats.enums.ModeloDeContratacaoEnum
import com.example.obrastats.viewmodel.ColaboradoresViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioColaborador(
    navController: NavController,
    colaboradoresVM: ColaboradoresViewModel
) {
    var colaboradorSelecionado = colaboradoresVM.getColaboradorSelecionado()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val nomeState = remember { mutableStateOf("") }
    val profissaoState = remember { mutableStateOf("") }
    val modeloContratoState = remember { mutableStateOf<ModeloDeContratacaoEnum?>(null) }
    val sexoState = remember { mutableStateOf<String?>(null) }
    val cpfCnpjState = remember { mutableStateOf<String?>("") }
    val celularState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val cidadeState = remember { mutableStateOf("") }
    val enderecoState = remember { mutableStateOf("") }
    val listaSexos = listOf("Masculino", "Feminino");

    if (colaboradorSelecionado != null) {
        nomeState.value = colaboradorSelecionado.nome
        profissaoState.value = colaboradorSelecionado.profissao
        modeloContratoState.value = colaboradorSelecionado.modeloDeContrato
        sexoState.value = colaboradorSelecionado.sexo
        cpfCnpjState.value = colaboradorSelecionado.cpfCnpj ?: ""
        celularState.value = colaboradorSelecionado.celular
        emailState.value = colaboradorSelecionado.email
        cidadeState.value = colaboradorSelecionado.cidade
        enderecoState.value = colaboradorSelecionado.endereco
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (colaboradorSelecionado != null) "Atualizar colaborador" else "Cadastrar colaborador") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("lista-colaboradores") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
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
                val maxLength = 14 // O comprimento máximo permitido para CPF ou CNPJ formatado
                OutlinedTextField(
                    value = cpfCnpjState.value.toString(),
                    onValueChange = { value ->
                        if (value.length <= maxLength) {
                            cpfCnpjState.value = value
                        }
                    },
                    label = { Text("CPF ou CNPJ") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                )
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
                        var colaborador = Colaborador(
                            if (colaboradorSelecionado != null) colaboradorSelecionado.id else null,
                            nomeState.value,
                            profissaoState.value,
                            modeloContratoState.value ?: ModeloDeContratacaoEnum.DIARISTA,
                            sexoState.value ?: "",
                            celularState.value,
                            emailState.value,
                            cidadeState.value,
                            enderecoState.value,
                            cpfCnpjState.value
                        )
                        scope.launch(Dispatchers.IO) {

                            colaboradoresVM.salvarColaborador(colaborador);

                        }
                        scope.launch(Dispatchers.Main) {
                            if (colaboradorSelecionado != null) {
                                Toast.makeText(
                                    context,
                                    "Colaborador cadastrado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Colaborador atualizado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                            navController.popBackStack()
                        }
                    },
                    enabled = nomeState.value.isNotBlank() && profissaoState.value.isNotBlank() && (sexoState.value?.isNotBlank() == true) && modeloContratoState.value != null && celularState.value.isNotBlank() && emailState.value.isNotBlank() && cidadeState.value.isNotBlank() && enderecoState.value.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (colaboradorSelecionado != null) "Atualizar" else "Cadastrar")
                }
            }
        })

}


//@Preview(showBackground = true)
//@Composable
//fun ColaboradorFormularioPreview() {
//    FormularioColaborador({})
//}