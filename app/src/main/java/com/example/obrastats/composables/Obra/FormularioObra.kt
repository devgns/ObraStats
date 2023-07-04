package com.example.obrastats.composables.Obra

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.obrastats.composables.dropDownForm
import com.example.obrastats.viewmodel.ClientesViewModel
import com.example.obrastats.viewmodel.ObrasViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioObra(navController: NavController, obrasVM: ObrasViewModel, clientesVM: ClientesViewModel) {

    val obraSelecionada = obrasVM.getObraSelecionada();
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val idState = remember { mutableStateOf("") }
    val nomeState = remember { mutableStateOf("") }
    val clienteState = remember { mutableStateOf<Cliente?>(null) }
    val cidadeState = remember { mutableStateOf("") }
    val enderecoState = remember { mutableStateOf("") }


    val clientesState = clientesVM.clientes.collectAsState(initial = mutableListOf())
    LaunchedEffect(Unit) {
        scope.launch {
            clientesVM.getClientes()
        }
    }

    if (obraSelecionada != null) {
        idState.value = obraSelecionada.id as String
        nomeState.value = obraSelecionada.nome
        clienteState.value = obraSelecionada.cliente
        cidadeState.value = obraSelecionada.cidade
        enderecoState.value = obraSelecionada.endereco
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if(false) "Atualizar obra" else "Cadastrar obra") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("lista-obras") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp).verticalScroll(
                rememberScrollState()
            )) {
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
                        clientesState.value,
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
                            if(obraSelecionada != null) obraSelecionada.id else null,
                            nomeState.value,
                            clienteState.value as Cliente,
                            cidadeState.value,
                            enderecoState.value
                        )
                        Log.i("obra", obra.toString() )
                        scope.launch(Dispatchers.IO) {
                            obrasVM.salvarObra(obra);
                        }
                        scope.launch(Dispatchers.Main) {
                            if (obraSelecionada != null) {
                                Toast.makeText(
                                    context,
                                    "Obra atualizada com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Obra cadastrada com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                            navController.popBackStack()
                        }
                    },
                    enabled = nomeState.value.isNotBlank() && cidadeState.value.isNotBlank() && enderecoState.value.isNotBlank() && clienteState.value != null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (obraSelecionada != null) "Atualizar" else "Cadastrar")
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