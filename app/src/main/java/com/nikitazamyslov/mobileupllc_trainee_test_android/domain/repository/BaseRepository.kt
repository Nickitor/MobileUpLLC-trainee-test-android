package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository

import android.util.Log
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import retrofit2.Response

interface BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {

        try {
            val response = apiCall()
            if (response.isSuccessful) {
                Log.e("Remote Api Service", "isSuccessful")
                val body = response.body()
                if (body != null) {
                    Log.e("Remote Api Service", body.toString())
                    return ApiResponse.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ApiResponse<T> {
        Log.e("Remote Api Service", message)
        return ApiResponse.Error("Network call has failed for a following reason: $message")
    }
}
