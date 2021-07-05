package com.dj.kmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.dj.kmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<String>?,
) {
    dialogQueue?.peek()?.let { message ->
        GenericDialog(title = "Error", description = message)
    }
}