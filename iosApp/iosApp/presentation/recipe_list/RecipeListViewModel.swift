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
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipes:
            doNothing()
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

