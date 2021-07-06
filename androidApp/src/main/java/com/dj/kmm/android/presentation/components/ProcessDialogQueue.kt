package com.dj.kmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.dj.kmm.domain.model.GenericMessageInfo
import com.dj.kmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadFromQueue: () -> Unit,
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            title = dialogInfo.title,
            description = dialogInfo.description,
            onRemoveHeadFromQueue = onRemoveHeadFromQueue,
            onDismiss = dialogInfo.onDismiss,
            negativeAction = dialogInfo.negativeAction,
            positiveAction = dialogInfo.positiveAction
        )
    }
}