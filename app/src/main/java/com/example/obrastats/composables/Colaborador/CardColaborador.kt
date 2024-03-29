package com.example.obrastats.composables.Colaborador

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
import com.example.obrastats.classes.Colaborador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColaboradorCard(colaborador: Colaborador, onEditClicked: () -> Unit) {
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
                    text = colaborador.nome,
                    fontSize = 20.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onEditClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar colaborador"
                    )
                }
            }
            Text(text = "Profissão: ${colaborador.profissao}")
            Text(text = "Modelo de Contrato: ${colaborador.modeloDeContrato}")
            Text(text = "Sexo: ${colaborador.sexo}")
            if (colaborador.cpfCnpj != null) {
                Text(text = "CPF / CNPJ: ${colaborador.cpfCnpj}")
            }
            Text(text = "Celular: ${colaborador.celular}")
            Text(text = "Email: ${colaborador.email}")
            Text(text = "Cidade: ${colaborador.cidade}")
            Text(text = "Endereço: ${colaborador.endereco}")
        }
    }
}

//@Preview
//@Composable
//fun ColaboradorCardPreview() {
//    ColaboradorCard(
//        Colaborador(
//            null,
//            "João",
//            "Eletricista",
//            ModeloDeContratacaoEnum.DIARISTA,
//            "Masculino",
//            "34999999999",
//            "joao@email.com",
//            "Uberaba",
//            "Rua Feliz"
//        )
//    )
//}
