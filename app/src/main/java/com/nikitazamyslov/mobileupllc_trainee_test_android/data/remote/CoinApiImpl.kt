package com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote

import com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.service.ApiService
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import retrofit2.Response
import javax.inject.Inject

class CoinApiImpl @Inject constructor(private val apiService: ApiService) : ICoinApi {
    override suspend fun getCoinList(currency: String): Response<List<CoinPrice>> {
        return apiService.getCoinList(currency)
    }

    override suspend fun getCoinDetail(id: String): Response<CoinDetail> {
        return apiService.getCoinDetail(id)
    }
}
