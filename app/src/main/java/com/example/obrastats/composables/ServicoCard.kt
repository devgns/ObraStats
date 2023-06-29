package com.example.obrastats.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra
import com.example.obrastats.classes.Servico
import com.example.obrastats.enums.SituacaoServicoEnum
import com.example.obrastats.R

@Composable
fun ServicoCard(servico: Servico, onEditClicked: () -> Unit, onDeleteClicked: () -> Unit) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf<Boolean>(false) }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = servico.descricao,
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
            Text(text = "Obra: ${servico.obra.nome}")
            Text(text = "Cliente: ${servico.obra.cliente.nome}")
            Text(text = "Valor Estimado: ${servico.valorEstimado}")
//            Text(text = "Data de início: ${servico.dataInicio}")
            Text(text = "Situação: ${servico.valorEstimado}")

            if (!expanded) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Mostrar mais",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Clique para mais informações",
                        tint = if (expanded) Color.Red else Color.Gray,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }
            }
        }
        if (expanded) {
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /* Ação do botão "Lançar diárias" */ },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth() ) {
                        Text(text = "Lançar diárias")
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            tint = Color.White,
                            contentDescription = "Ícone de adição"
                        )
                    }
                }
                Button(
                    onClick = { /* Ação do botão "Lançar custos" */ },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Lançar custos")
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Ícone de adição"
                        )
                    }
                }
                Button(
                    onClick = { /* Ação do botão "Lançar receitas" */ },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,  horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Lançar receitas")
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "Ícone de adição"
                        )
                    }
                }
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),

            ) {
                Button(
                    onClick = { /* Ação do botão "Linha do tempo" */ },
                    modifier = Modifier.fillMaxWidth(0.6f)

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround ,modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Linha do tempo")
                        Icon(
                            painterResource(id = R.drawable.history),
                            contentDescription = "Ícone da linha do tempo"
                        )
                    }
                }
                Button(
                    onClick = { /* Ação do botão "Ver estatísticas" */ },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround ,modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Ver estatísticas")
                        Icon(
                            painterResource(id = R.drawable.statistics),
                            contentDescription = "Ícone de estatísticas"
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Text(
                        text = "Mostrar menos",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Clique para mais informações",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 6.dp)
                            .rotate(180f)
                    )
                }
            }

        }
    }
}


@Preview
@Composable
fun ServicoCardPreview() {
//    val navController = rememberNavController()
    val cliente = Cliente(
        id = "1",
        nome = "João Silva",
        sexo = "Masculino",
        celular = "999999999",
        email = "joao@example.com",
        cidade = "São Paulo",
        endereco = "Rua A, 123",
        cpfCnpj = "1234567890"
    )

    val obra = Obra(
        id = "1",
        nome = "Obra 1",
        cliente = cliente,
        cidade = "São Paulo",
        endereco = "Rua B, 456"
    )

    val servico = Servico(
        id = "1",
        descricao = "Serviço de pintura",
        obra = obra,
        valorEstimado = 1500.0,
//        dataInicio = LocalDate.now(),
        situacaoServico = SituacaoServicoEnum.EMANDAMENTO
    )
    ServicoCard(servico, { }, {})
}