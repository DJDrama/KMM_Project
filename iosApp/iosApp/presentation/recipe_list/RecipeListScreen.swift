//
//  RecipeListScreen.swift
//  iosApp
//
//  Created by dj on 2021/07/08.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    
    @ObservableObject var viewModel: RecipeListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
        self.viewModel = RecipeListViewModel(searchRecipes: searchRecipesModule.searchRecipes, foodCategoryUtil: FoodCategoryUtil())
    }
    
    var body: some View {
        List{
            ForEach(viewModel.state.recipes, id: \.self.id){ recipe in
                Text(recipe.title).onAppear(perform: {
                    if viewModel.shouldQueryNextPage(recipe: recipe){
                        viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                    }
                })
            }
        }
    }
}
