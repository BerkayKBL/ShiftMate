package com.berkaykbl.shiftmate.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EntryDialog(
    enabled: Boolean,
    title: String,
    textComponents: @Composable (() -> Unit),
    confirmCallback: () -> Unit,
    dismissCallback: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    if (enabled) {
        AlertDialog(
            onDismissRequest = {
                dismissCallback()
            },
            confirmButton = {
                Button(onClick = confirmCallback) {
                    Text(text = "Save")
                }
            },
            containerColor = colorScheme.primary,
            titleContentColor = colorScheme.secondary,
            textContentColor = colorScheme.secondary,
            title = {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = textComponents
        )
    }

}