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
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
    }
    var body: some View {
        Text("RecipeList Screen")
    }
}
//
//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
