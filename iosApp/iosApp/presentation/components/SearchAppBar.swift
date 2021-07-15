//
//  SearchAppBar.swift
//  iosApp
//
//  Created by dj on 2021/07/15.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchAppBar: View {
    @State var query: String = ""
    private let onTriggerEvent: (RecipeListEvents) -> Void
    
    init(query: String,
         onTriggerEvent: @escaping (RecipeListEvents) -> Void
    ){
        self.onTriggerEvent = onTriggerEvent
        self._query = State(initialValue: query)
    }
    
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField("Search...", text: $query, onCommit:{
                    onTriggerEvent(RecipeListEvents.NewSearch())
                }).onChange(of: query, perform: { value in
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(query: value))
                })
            }.padding(.bottom, 8)
        }.padding(.top, 8)
        .padding(.horizontal, 8)
        .background(Color.white.opacity(0))
    }
}

//struct SearchAppBar_Previews: PreviewProvider {
//    static var previews: some View {
//        SearchAppBar()
//    }
//}
