package com.dj.kmm.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dj.kmm.presentation.recipe_list.FoodCategory
import com.dj.kmm.presentation.recipe_list.FoodCategoryUtil

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    selectedCategory: FoodCategory? = null,
    onQueryChanged: (String) -> Unit,
    onSelectedCategoryChanged: (FoodCategory) -> Unit,
    categories: List<FoodCategory>,
    onExecuteSearch: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp,
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(8.dp),
                    value = query,
                    onValueChange = onQueryChanged,
                    label = {
                        Text(text = "Search...")
                    }, keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()
                        }
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
            }
            LazyRow(modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)) {
                items(categories) {
                    FoodCategoryChip(
                        category = it.value,
                        onSelectedCategoryChanged = {
                            FoodCategoryUtil().getFoodCategory(it)?.let { newCategory ->
                                onSelectedCategoryChanged(newCategory)
                            }
                        },
                        isSelected = selectedCategory == it
                    )
                }
            }
        }
    }
}