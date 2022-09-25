package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinListUseCase
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinListUseCase: GetCoinListUseCase) :
    ViewModel() {

    private var _state: MutableStateFlow<ApiResponse<List<CoinPrice>>> =
        MutableStateFlow(ApiResponse.Loading)
    val state get() = _state

    init {
        getCoinList()
    }

    fun getCoinList() {
        viewModelScope.launch {
            getCoinListUseCase.getCoinListUseCase("usd").collect { coinList ->
                _state.value = coinList
            }
        }
    }
}