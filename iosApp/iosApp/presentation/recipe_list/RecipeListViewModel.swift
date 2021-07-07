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
    
    
}

