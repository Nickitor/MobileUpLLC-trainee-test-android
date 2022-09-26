package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(private val repository: ICoinRepository) {

    suspend fun getCoinDetailUseCase(currency: String) = repository.getCoinDetail(currency)
}
