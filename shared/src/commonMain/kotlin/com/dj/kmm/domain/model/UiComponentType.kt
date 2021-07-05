package com.dj.kmm.domain.model

sealed class UIComponentType {
    object Dialog : UIComponentType()
    //object SnackBar: UiComponentType()

    object None : UIComponentType()
}