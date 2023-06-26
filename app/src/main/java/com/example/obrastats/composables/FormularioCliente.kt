package com.example.obrastats.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.obrastats.classes.Cliente
import com.example.obrastats.viewmodel.ClientesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCliente(navController: NavController, clientesVM: ClientesViewModel) {

    val clienteSelecionado = clientesVM.getClienteSelecionado();
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    val idState = remember { mutableStateOf("") }
    val nomeState = remember { mutableStateOf("") }
    val sexoState = remember { mutableStateOf<String?>(null) }
    val celularState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val cidadeState = remember { mutableStateOf("") }
    val enderecoState = remember { mutableStateOf("") }

    val isEmailTouched = remember { mutableStateOf(false) }

    val listaSexos = listOf("Masculino", "Feminino")

    if (clienteSelecionado != null) {

        idState.value = clienteSelecionado.id as String
        nomeState.value = clienteSelecionado.nome
        sexoState.value = clienteSelecionado.sexo
        celularState.value = clienteSelecionado.celular
        emailState.value = clienteSelecionado.email
        cidadeState.value = clienteSelecionado.cidade
        enderecoState.value = clienteSelecionado.endereco
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (clienteSelecionado != null) "Atualizar cliente" else "Cadastrar cliente") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("lista-clientes") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).padding(16.dp),
            ) {
                Text("Cadastro de Cliente", style = TextStyle(fontSize = 20.sp))

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nomeState.value.toString(),
                    onValueChange = { value -> nomeState.value = value },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
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
                    onValueChange = { value ->
                        emailState.value = value
                        if (!isEmailTouched.value) {
                            isEmailTouched.value = true
                        }
                    },
                    label = { Text("Email") },
                    isError = isEmailTouched.value && !isEmailValid(emailState.value),
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
                        val cliente = Cliente(
                            if (clienteSelecionado != null) clienteSelecionado.id else null,
                            nomeState.value,
                            sexoState.value ?: "",
                            celularState.value,
                            emailState.value,
                            cidadeState.value,
                            enderecoState.value
                        )
                        scope.launch(Dispatchers.IO) {
                            clientesVM.salvarCliente(cliente);
                        }

                        scope.launch(Dispatchers.Main) {
                            if (clienteSelecionado != null) {
                                Toast.makeText(
                                    context,
                                    "Cliente cadastrado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Cliente atualizado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                            navController.popBackStack()
                        }
                    },
                    enabled = isEmailValid(emailState.value) && nomeState.value.isNotBlank() && celularState.value.isNotBlank() && cidadeState.value.isNotBlank() && enderecoState.value.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (clienteSelecionado != null) "Atualizar" else "Cadastrar")
                }
            }
        },
    )

}

fun isEmailValid(email: String): Boolean {
    // Implemente aqui a validação específica para o campo de email
    // Exemplo básico: Verificar se o email possui um formato válido usando regex
    val emailRegex = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}".toRegex()
    return email.matches(emailRegex)
}

@Preview(showBackground = true)
@Composable
fun FormularioClientePreview() {
    val clientesViewModel: ClientesViewModel = ClientesViewModel();
    val navController = rememberNavController()
    FormularioCliente(navController, clientesViewModel)
}
