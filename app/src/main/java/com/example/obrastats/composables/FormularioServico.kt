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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.obrastats.classes.Obra
import com.example.obrastats.enums.SituacaoServicoEnum
import com.example.obrastats.viewmodel.ObrasViewModel
import com.example.obrastats.viewmodel.ServicosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import com.example.obrastats.classes.Servico

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioServico(
    navController: NavController,
    servicosVM: ServicosViewModel,
    obrasVM: ObrasViewModel
) {

    val servicoSelecionado = servicosVM.getServicoSelecionado();
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val idState = remember { mutableStateOf("") }
    val descricaoState = remember { mutableStateOf("") }
    val obraState = remember { mutableStateOf<Obra?>(null) }
    val valorEstimadoState = remember { mutableStateOf<Double?>(null) }
    val dataInicioState = remember { mutableStateOf<LocalDate?>(null) }
    val situacaoServicoState = remember { mutableStateOf<SituacaoServicoEnum?>(null) }


//    val dateTime = LocalDateTime.now()
//    val millis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
//
//    val datePickerState = remember {
//        DatePickerState(
//            yearRange = (2000..2024),
//            initialSelectedDateMillis =millis,
//            initialDisplayMode = DisplayMode.Input,
//            initialDisplayedMonthMillis = null
//        )
//    }


    val obrasState = obrasVM.obras.collectAsState(initial = mutableListOf())
    LaunchedEffect(Unit) {
        scope.launch {
            obrasVM.getObras()
        }
    }

    if (servicoSelecionado != null) {
        idState.value = servicoSelecionado.id as String
        descricaoState.value = servicoSelecionado.descricao
        obraState.value = servicoSelecionado.obra
        valorEstimadoState.value = servicoSelecionado.valorEstimado
//        dataInicioState.value = servicoSelecionado.dataInicio
        situacaoServicoState.value = servicoSelecionado.situacaoServico

    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (false) "Atualizar serviço" else "Cadastrar serviço") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("lista-servicos") }) {
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
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                Text("Cadastro do Serviço", style = TextStyle(fontSize = 20.sp))

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = descricaoState.value,
                    onValueChange = { value -> descricaoState.value = value },
                    label = { Text("Descrição do servico") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    dropDownForm(
                        obrasState.value,
                        placeHolder = "Obra",
                        selectedItem = obraState,
                        itemToString = { it.nome + " - Cliente:  " + it.cliente.nome }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = valorEstimadoState.value?.toString() ?: "",
                    onValueChange = { value ->
                        valorEstimadoState.value = value.toDoubleOrNull()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Valor estimado") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                dropDownForm(
                    SituacaoServicoEnum.values().toList(),
                    placeHolder = "Situação do serviço",
                    selectedItem = situacaoServicoState,
                    itemToString = { it?.descricao ?: "" }
                )
                Spacer(modifier = Modifier.height(16.dp))

//                DatePicker(
//                    state = datePickerState,
//                    showModeToggle = true,
//                    )
                Button(
                    onClick = {
                        Log.i("servico", "descricaoState -> " +descricaoState.value )
                        Log.i("servico", "obrasState -> " +obraState.value.toString() )
                        Log.i("servico", "valorEstimadoState -> " +valorEstimadoState.value )
                        Log.i("servico", "situacaoServicoState -> " +situacaoServicoState.value )

                        val servico = Servico(
                            if (servicoSelecionado != null) servicoSelecionado.id else null,
                            descricaoState.value,
                            obraState.value as Obra,
                            valorEstimadoState.value as Double,
                            situacaoServicoState.value as SituacaoServicoEnum
                        )
                        Log.i("servicoo", servico.toString())

                        scope.launch(Dispatchers.IO) {
                            servicosVM.salvarServico(servico);
                        }
                        scope.launch(Dispatchers.Main) {
                            if (servicoSelecionado != null) {
                                Toast.makeText(
                                    context,
                                    "Serviço cadastrado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Serviço atualizado com sucesso",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                            navController.popBackStack()
                        }
                    },
                    enabled = descricaoState.value.isNotBlank() && obraState.value != null && valorEstimadoState.value != null && (valorEstimadoState.value ?: 0.0 >= 0) && situacaoServicoState.value != null,

                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (servicoSelecionado != null) "Atualizar" else "Cadastrar")
                }
            }

        }
    )
}