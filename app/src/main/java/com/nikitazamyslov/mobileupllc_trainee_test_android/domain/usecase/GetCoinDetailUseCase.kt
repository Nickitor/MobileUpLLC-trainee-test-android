package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository

class GetCoinDetailUseCase(private val repository: ICoinRepository) {

    suspend fun getCoinDetailUseCase(currency: String) = repository.getCoinList(currency)
}