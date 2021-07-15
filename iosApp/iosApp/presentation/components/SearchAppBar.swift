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
    
    
    
    var body: some View {
        VStack{
            HStack{
                Image(systemName: "magnifyingglass")
                TextField("Search...", text: $query, onCommit:{
                    
                }).onChange(of: query, perform: { value in
                    // TODO("Update the query")
                })
            }.padding(.bottom, 8)
        }.padding(.top, 8)
        .padding(.horizontal, 8)
        .background(Color.white.opacity(0))
    }
}

struct SearchAppBar_Previews: PreviewProvider {
    static var previews: some View {
        SearchAppBar()
    }
}
