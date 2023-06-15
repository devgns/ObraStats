package com.example.obrastats.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> dropDownForm(
    list: List<T>,
    placeHolder: String,
    selectedItem: MutableState<T?>,
    itemToString: (T) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    var textFilledSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedItem.value?.let(itemToString) ?: "",
            onValueChange = { /* Handle value change if needed */ },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFilledSize = coordinates.size.toSize()
                },
            shape = RoundedCornerShape(12.dp),
            label = {
                Text(text = placeHolder)
            },
            trailingIcon = {
                Icon(icon, null, Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFilledSize.width.toDp() })
        ) {
            list.forEach { item ->
                val label = itemToString(item)
                DropdownMenuItem(
                    onClick = {
                        selectedItem.value = item
                        expanded = false
                    },
                    text = { Text(text = label) }
                )
            }
        }
    }
}



