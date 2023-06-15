package com.example.obrastats

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.obrastats.composables.ClientesMainScreen
import com.example.obrastats.ui.theme.ObraStatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ObraStatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ClientesMainScreen()
                }
            }
        }
    }
}

@Composable
fun InitialMenu(
    modifier: Modifier = Modifier.fillMaxHeight()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxHeight()
        ) {
        items(menuItems) { item ->
            MenuButton(text = item.text, route = item.route)
        }
    }
}

@Composable
fun MenuButton(
    @StringRes text: Int,
    @StringRes route: Int,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.height(40.dp).clickable(
            onClick = {Toast.makeText(context, text, Toast.LENGTH_SHORT).show()}
        )

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = stringResource(id = text),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun InitialMenuPreview() {
    ObraStatsTheme {
        InitialMenu()
    }
}

private val menuItems = listOf(
    R.string.clientes to R.string.rota_lista_clientes,
    R.string.obras to R.string.rota_lista_obras,
    R.string.funcionarios to R.string.rota_lista_funcionarios,
    R.string.servicos to R.string.rota_lista_servicos,
    R.string.analises to R.string.rota_lista_analises
).map { MenuItemRouteStringPair(it.first, it.second) }




//IconButton(onClick = { expanded = !expanded }) {
//    Icon(
//        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
//        contentDescription = if (expanded) {
//            stringResource(R.string.show_less)
//        } else {
//            stringResource(R.string.show_more)
//        }
//    )
//}


private data class MenuItemRouteStringPair(
    @StringRes val text: Int,
    @StringRes val route: Int
)