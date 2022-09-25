package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import kotlinx.coroutines.flow.Flow

interface ICoinRepository : BaseRepository {

    suspend fun getCoinList(currency: String): Flow<ApiResponse<List<CoinPrice>>>

    suspend fun getCoinDetail(id: String): Flow<ApiResponse<CoinDetail>>
}