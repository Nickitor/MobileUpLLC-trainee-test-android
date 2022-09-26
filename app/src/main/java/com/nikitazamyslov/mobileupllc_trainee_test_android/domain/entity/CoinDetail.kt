package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity

data class CoinDetail(
    val id: String,
    val name: String,
    val image: CoinImage,
    val description: Description,
    val categories: List<String>,
)
