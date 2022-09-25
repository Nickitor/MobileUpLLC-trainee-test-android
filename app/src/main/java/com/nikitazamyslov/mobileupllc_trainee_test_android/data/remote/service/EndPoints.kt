package com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.service

class EndPoints {

    companion object {

        const val BASE_URL = "https://api.coingecko.com/api/v3/"

        const val GET_COIN_LIST = "coins/markets"
        const val GET_COIN_DETAIL = "coins/{id}"
    }
}
