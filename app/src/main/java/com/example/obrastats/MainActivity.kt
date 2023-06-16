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
import androidx.compose.foundation.layout.Spacer
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
import com.example.obrastats.composables.TelaPrincipalClientes
import com.example.obrastats.ui.theme.ObraStatsTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.obrastats.composables.FormularioCliente
import com.example.obrastats.composables.FormularioColaborador
import com.example.obrastats.composables.FormularioObra
import com.example.obrastats.composables.TelaPrincipalColaboradores
import com.example.obrastats.composables.TelaPrincipalObras
import com.example.obrastats.viewmodel.ClientesViewModel

class MainActivity : ComponentActivity() {

    val clientesViewModel: ClientesViewModel = ClientesViewModel();
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ObraStatsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        MainScreen(navController)
                    }

                    navigation("lista", "clientes") {
                        composable("lista") {
                            TelaPrincipalClientes(navController,clientesViewModel)
                        }
                        composable("criar-editar-cliente") {
                            FormularioCliente(navController,clientesViewModel)
                        }
                    }

                    navigation("lista", "colaboradores"){
                        composable("lista"){
                            TelaPrincipalColaboradores()
                        }
                        composable("criar-editar"){
                            FormularioColaborador({})
                        }
                    }

                    navigation("lista", "obras"){
                        composable("lista"){
                            TelaPrincipalObras()
                        }
                        composable("criar-editar"){
                            FormularioObra({}, {})
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier.fillMaxHeight()) {
    val selectedItem = remember { mutableStateOf(-1) }

    val menuItems = listOf(
        R.string.clientes to "clientes",
        R.string.obras to "obras",
        R.string.colaboradores to "colaboradores",
    ).map { MenuItemRouteStringPair(it.first, it.second) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(menuItems) { item ->
            MenuButton(
                text = item.text,
                route = item.route,
                modifier = Modifier.clickable { navController.navigate(item.route) }
            )
        }
    }
}

@Composable
fun MenuButton(
    @StringRes text: Int,
     route: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier.height(70.dp)
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
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InitialMenuPreview() {
    ObraStatsTheme {
        val navController = rememberNavController()

        MainScreen(navController)
    }
}
private data class MenuItemRouteStringPair(
    @StringRes val text: Int,
     val route: String
)


//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainScreen()
//                }