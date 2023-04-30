package com.lego.myladder.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmActionDialog(
    message: String,
    title: String = "Подтверждение действия",
    okLabel: String = "Подтвердить",
    dismissLabel: String = "Отмена",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text(text = okLabel)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(text = dismissLabel)
            }
        }
    )
}