package com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.service

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(EndPoints.GET_COIN_LIST)
    suspend fun getCoinList(
        @Query("vs_currency") currency: String,
        @Query("per_page") pages: Int = 100,
    ): Response<List<CoinPrice>>

    @GET(EndPoints.GET_COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = false,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false,
    ): Response<CoinDetail>
}
