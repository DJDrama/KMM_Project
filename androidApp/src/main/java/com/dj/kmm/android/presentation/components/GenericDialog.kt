package com.dj.kmm.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dj.kmm.domain.model.NegativeAction
import com.dj.kmm.domain.model.PositiveAction

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadFromQueue: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { // click outside of dialog
            onDismiss?.invoke()
            onRemoveHeadFromQueue()
        },
        text = {
            description?.let {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        buttons = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.End) {
                negativeAction?.let {
                    Button(modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        onClick = {
                            it.onNegativeAction()
                            onRemoveHeadFromQueue()
                        }
                    ) {
                        Text(
                            text = it.negativeBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
                positiveAction?.let {
                    Button(modifier = Modifier.padding(end = 8.dp),
                        onClick = {
                            it.onPositiveAction()
                            onRemoveHeadFromQueue()
                        }
                    ) {
                        Text(
                            text = it.positiveBtnTxt,
                            style = MaterialTheme.typography.button
                        )
                    }
                }

            }
        }
    )
}