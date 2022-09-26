package com.nikitazamyslov.mobileupllc_trainee_test_android.presentation.coin_detail_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinDetail
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.entity.CoinPrice
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinDetailUseCase
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.wrapper.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val getCoinDetailUseCase: GetCoinDetailUseCase) :
    ViewModel() {

    private var _state: MutableSharedFlow<ApiResponse<CoinDetail>> = MutableSharedFlow(1)
    val state get() = _state

    fun getCoinDetail(id: String?) {
        viewModelScope.launch {
            _state.emit(ApiResponse.Loading)
            if (id != null) getCoinDetailUseCase.getCoinDetailUseCase(id).collect { coinDetail ->
                _state.emit(coinDetail)
            }
            else _state.emit(ApiResponse.Error(""))
        }
    }
}