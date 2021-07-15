//
//  RecipeListViewModel.swift
//  iosApp
//
//  Created by dj on 2021/07/08.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    // dependencies
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil
    
    // Observing
    @Published var state: RecipeListState = RecipeListState()
    
    init(searchRecipes: SearchRecipes, foodCategoryUtil: FoodCategoryUtil){
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        
        // TODO("Perform Search")
        onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            newSearch()
        case is RecipeListEvents.NextPage:
            nextPage()
        case is RecipeListEvents.OnUpdateQuery:
            onUpdateQuery(query: (stateEvent as! RecipeListEvents.OnUpdateQuery).query)
        case is RecipeListEvents.OnSelectCategory:
            doNothing()
        case is RecipeListEvents.OnRemoveHeadMessageFromQueue:
            doNothing()
            
        default:
            doNothing()
        }
    }
    
    
    private func nextPage(){
        let currentState = self.state.copy() as! RecipeListState
        updateState(page: Int(currentState.page) + 1)
        loadRecipes()
    }
    
    private func loadRecipes(){
        let currentState = (self.state.copy() as! RecipeListState)
        do {
            try searchRecipes.execute(page: Int32(currentState.page), query: currentState.query)
                .collectCommon(
                    coroutineScope: nil,
                    callback: { dataState in
                        if dataState != nil {
                            let data = dataState?.data
                            let message = dataState?.message
                            let loading = dataState?.isLoading ?? false
                            self.updateState(isLoading: loading)
                            if data!=nil {
                                self.appendRecipes(recipes: data as! [Recipe])
                            }
                            if message != nil {
                                self.handleMessageByUIComponentType(message!.build())
                            }
                            
                        }
                    }
                )
        }catch{
            print("\(error)")
        }
    }
    
    private func newSearch(){
        resetSearchState()
        loadRecipes()
    }
    
    private func resetSearchState(){
        let currentState = (self.state.copy() as! RecipeListState)
        var foodCategory = currentState.selectedCategory
        if(foodCategory?.value != currentState.query){
            foodCategory = nil // unhighlight chip
        }
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1, // reset
            query: currentState.query,
            selectedCategory: foodCategory, // reset
            recipes: [], // reset
            bottomRecipe: currentState.bottomRecipe,
            queue: currentState.queue
        )
    }
    
    private func onUpdateQuery(query: String){
        updateState(query: query)
    }
    
    private func onUpdateBottomRecipe(recipe: Recipe){
        updateState(bottomRecipe: recipe)
    }
    
    func appendRecipes(recipes: [Recipe]){
        var currentState = (self.state.copy() as! RecipeListState)
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentOf: recipes)
        
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentRecipes,
            bottomRecipe: currentState.bottomRecipe,
            queue: currentState.queue
        )
        currentState = self.state.copy() as! RecipeListState
        self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count-1])
    }
    
    func shouldQueryNextPage(recipe: Recipe) -> Bool {
        let currentState = self.state.copy() as! RecipeListState
        if(recipe.id == currentState.bottomRecipe?.id){
            if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.recipes.count){
                if(!currentState.isLoading){
                    return true
                }
            }
        }
        return false
    }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        // TODO("append to queue or None")
    }
    
    func doNothing(){
        //does nothing
    }
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        bottomRecipe: Recipe?=nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(isLoading: isLoading ?? currentState.isLoading,
                                       page: Int32(page ?? Int(currentState.page)),
                                       query: query ?? currentState.query,
                                       selectedCategory: currentState.selectedCategory,
                                       recipes: currentState.recipes,
                                       bottomRecipe: bottomRecipe ?? currentState.bottomRecipe,
                                       queue: queue ?? currentState.queue)
    }
}

