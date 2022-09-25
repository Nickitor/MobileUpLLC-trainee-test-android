package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_detail_feature

import androidx.lifecycle.ViewModel
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinDetailUseCase
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val getCoinDetailUseCase: GetCoinDetailUseCase) :
    ViewModel()