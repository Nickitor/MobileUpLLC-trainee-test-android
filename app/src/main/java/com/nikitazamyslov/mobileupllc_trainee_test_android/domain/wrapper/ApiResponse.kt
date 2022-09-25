package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.Status

data class ApiResponse<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> loading(data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.LOADING, data, null)
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.ERROR, data, message)
        }
    }
}