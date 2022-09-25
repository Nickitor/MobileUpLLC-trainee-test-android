package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity

data class CoinDetail(
    val id: String,
    val image: CoinImage,
    val description: String,
    val categories: List<String>,
)