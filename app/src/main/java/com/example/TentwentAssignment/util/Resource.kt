package com.example.TentwentAssignment.util

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        CACHE,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> Success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> Cache(data: T?): Resource<T> {
            return Resource(
                Status.CACHE,
                data,
                null
            )
        }

        fun <T> Error(msg: String, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> Loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}