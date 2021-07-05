package com.dj.kmm.domain.util

import com.dj.kmm.domain.model.GenericMessageInfo

data class DataState<T>(
    val message: GenericMessageInfo? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
) {

    companion object {

        fun <T> error(
            message: GenericMessageInfo,
        ): DataState<T> {
            return DataState(
                message = message,
                data = null,
            )
        }

        fun <T> data(
            message: GenericMessageInfo? = null,
            data: T? = null,
        ): DataState<T> {
            return DataState(
                message = message,
                data = data,
            )
        }

        fun <T>loading() = DataState<T>(isLoading = true)
    }
}