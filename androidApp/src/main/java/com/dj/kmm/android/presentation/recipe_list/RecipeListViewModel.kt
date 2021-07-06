package com.dj.kmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dj.kmm.domain.model.GenericMessageInfo
import com.dj.kmm.domain.model.NegativeAction
import com.dj.kmm.domain.model.PositiveAction
import com.dj.kmm.domain.model.Recipe
import com.dj.kmm.domain.model.UIComponentType
import com.dj.kmm.domain.util.GenericMessageInfoQueueUtil
import com.dj.kmm.domain.util.Queue
import com.dj.kmm.interactors.recipe_list.SearchRecipes
import com.dj.kmm.presentation.recipe_list.FoodCategory
import com.dj.kmm.presentation.recipe_list.RecipeListEvents
import com.dj.kmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes,
) : ViewModel() {
    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)

        // Test
        // val messageInfoBuilder = GenericMessageInfo.Builder()
        //     .id(UUID.randomUUID().toString())
        //     .title("Weird")
        //     .uiComponentType(UIComponentType.Dialog)
        //     .description("Test")
        //     .positive(positiveAction = PositiveAction(
        //         positiveBtnTxt = "Ok", onPositiveAction = {
        //             state.value = state.value.copy(query = "Kale")
        //             onTriggerEvent(RecipeListEvents.NewSearch)
        //         }
        //     ))
        //     .negative(NegativeAction("Cancel", onNegativeAction = {
        //         state.value = state.value.copy(query = "Chicken")
        //         onTriggerEvent(RecipeListEvents.NewSearch)
        //     }))
        // appendToMessageQueue(messageInfo = messageInfoBuilder)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            is RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            is RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.NewSearch -> {
                newSearch()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(event.category)
            }
            is RecipeListEvents.OnRemovedHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description("Invalid Event")
                )
            }
        }
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }
            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
    }

    private fun onSelectCategory(foodCategory: FoodCategory) {
        state.value = state.value.copy(selectedCategory = foodCategory, query = foodCategory.value)
        newSearch()
    }

    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun appendRecipes(recipes: List<Recipe>) {

        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue, messageInfo = messageInfo.build()
            )
        ) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf())) // force recompose
        } catch (e: Exception) {
            //nothing to remove, queue is empty
        }
    }
}