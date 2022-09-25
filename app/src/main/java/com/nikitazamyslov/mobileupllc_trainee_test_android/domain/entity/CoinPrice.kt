package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity

import com.google.gson.annotations.SerializedName

data class CoinPrice(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: Double,
)