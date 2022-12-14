package com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase

import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(private val repository: ICoinRepository) {

    suspend fun getCoinListUseCase(currency: String) = repository.getCoinList(currency)
}
