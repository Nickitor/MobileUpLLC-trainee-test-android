package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository

class GetCoinListUseCase(private val repository: ICoinRepository) {

    suspend fun getCoinListUseCase(id: String) = repository.getCoinDetail(id)
}