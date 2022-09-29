package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_list_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinListUseCase
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val getCoinListUseCase: GetCoinListUseCase) :
    ViewModel() {

    private var _state: MutableSharedFlow<ApiResponse<List<CoinPrice>>> = MutableSharedFlow(1)
    val state get() = _state

    var currency = "USD"

    init {
        getCoinList()
    }

    fun getCoinList() {
        viewModelScope.launch {
            _state.emit(ApiResponse.Loading)
            getCoinListUseCase.getCoinListUseCase(currency).collect { coinList ->
                _state.emit(coinList)
            }
        }
    }

    fun currentCurrencyChanged(newCurrency: String) {
        currency = newCurrency
        getCoinList()
    }
}
