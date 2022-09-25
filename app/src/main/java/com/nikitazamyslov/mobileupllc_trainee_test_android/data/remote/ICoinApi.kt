package com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import retrofit2.Response

interface ICoinApi {

    suspend fun getCoinList(currency: String): Response<List<CoinPrice>>

    suspend fun getCoinDetail(id: String): Response<CoinDetail>
}