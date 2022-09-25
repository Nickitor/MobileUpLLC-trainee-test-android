package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository

import android.util.Log
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import retrofit2.Response

interface BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {

        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return ApiResponse.success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ApiResponse<T> {
        Log.e("remoteDataSource", message)
        return ApiResponse.error("Network call has failed for a following reason: $message")
    }
}