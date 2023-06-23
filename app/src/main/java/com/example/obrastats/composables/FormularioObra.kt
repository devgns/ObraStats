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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra
import com.example.obrastats.viewmodel.ClientesViewModel
import com.example.obrastats.viewmodel.ObrasViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioObra(navController: NavController, obrasViewModel: ObrasViewModel, clientesViewModel: ClientesViewModel) {

    val currentIndex: Int? = obrasViewModel.getCurrentIndex();

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val nomeState = remember { mutableStateOf("") }
    val clienteState = remember { mutableStateOf<Cliente?>(null) }
    val cidadeState = remember { mutableStateOf("") }
    val enderecoState = remember { mutableStateOf("") }

    val listaClientesState = remember { mutableStateListOf<Cliente>() }
    LaunchedEffect(Unit) {
        val clientesList = clientesViewModel.getListaClientes()
        listaClientesState.addAll(clientesList)
    }

    if (currentIndex != null) {
        val obra = obrasViewModel.getObrasList()[currentIndex]
        nomeState.value = obra.nome
        clienteState.value = obra.cliente
        cidadeState.value = obra.cidade
        enderecoState.value = obra.endereco
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if(currentIndex != null) "Atualizar obra" else "Cadastrar obra") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("obras") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
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

                Row(modifier = Modifier.fillMaxWidth()) {
                    dropDownForm(
                        listaClientesState,
                        placeHolder = "Cliente",
                        selectedItem = clienteState,
                        itemToString = {  it.nome }
                    )
                }
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
                        val obra = Obra(
                            null,
                            nomeState.value,
                            clienteState.value ?: Cliente(null, "", "", "", "", "", ""),
                            cidadeState.value,
                            enderecoState.value
                        )

                        if (currentIndex == null) {
                            obrasViewModel.addObra(obra);
                            Toast.makeText(
                                context,
                                "Obra cadastrada com sucesso",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        } else {
                            obrasViewModel.updateObraAtIndex(currentIndex, obra);
                            Toast.makeText(
                                context,
                                "Obra atualizada com sucesso",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                        obrasViewModel.changeIndex(null);
                        navController.navigate("obras");
                    },
                    enabled = nomeState.value.isNotBlank() && cidadeState.value.isNotBlank() && enderecoState.value.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (currentIndex != null) "Atualizar" else "Cadastrar")
                }
            }

        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun ObraFormularioPreview() {
//    FormularioObra({}, {})
//}