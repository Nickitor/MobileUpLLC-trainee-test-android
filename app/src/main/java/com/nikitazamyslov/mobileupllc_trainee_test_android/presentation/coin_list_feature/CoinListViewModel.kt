package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import androidx.lifecycle.ViewModel
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinListUseCase: GetCoinListUseCase) :
    ViewModel()
