package com.nikitazamyslov.mobileupllc_trainee_test_android.data.repository

import com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.ICoinApi
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CoinRepositoryImpl(private val api: ICoinApi) : ICoinRepository {

    override suspend fun getCoinList(currency: String): Flow<ApiResponse<List<CoinPrice>>> {
        return flow {
            emit(safeApiCall { api.getCoinList(currency) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCoinDetail(id: String): Flow<ApiResponse<CoinDetail>> {
        return flow {
            emit(safeApiCall { api.getCoinDetail(id) })
        }.flowOn(Dispatchers.IO)
    }
}