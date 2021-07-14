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
        case is RecipeListEvents.NextPage:
            doNothing()
        case is RecipeListEvents.OnUpdateQuery:
            doNothing()
        case is RecipeListEvents.OnSelectCategory:
            doNothing()
        case is RecipeListEvents.OnRemoveHeadMessageFromQueue:
            doNothing()
            
        default:
            doNothing()
        }
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
    
    func appendRecipes(recipes: [Recipe]){
        for recipe in recipes {
            print("\(recipe.title)")
        }
        // TODO("append recipes to state")
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
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(isLoading: isLoading ?? currentState.isLoading,
                                       page: Int32(page ?? Int(currentState.page)),
                                       query: query ?? currentState.query,
                                       selectedCategory: currentState.selectedCategory,
                                       recipes: currentState.recipes,
                                       queue: queue ?? currentState.queue)
    }
}

