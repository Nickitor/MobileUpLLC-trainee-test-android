package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    object Loading : ApiResponse<Nothing>()
    data class Error(val message: String) : ApiResponse<Nothing>()
}