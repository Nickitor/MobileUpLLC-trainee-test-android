package com.nikitazamyslov.mobileupllc_trainee_test_android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.CoinApiImpl
import com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.ICoinApi
import com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.service.ApiService
import com.nikitazamyslov.mobileupllc_trainee_test_android.data.remote.service.EndPoints
import com.nikitazamyslov.mobileupllc_trainee_test_android.data.repository.CoinRepositoryImpl
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.repository.ICoinRepository
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinDetailUseCase
import com.nikitazamyslov.mobileupllc_trainee_test_android.domain.usecase.GetCoinListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesBaseUrl() = EndPoints.BASE_URL

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(EndPoints.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideCoinApi(apiService: ApiService): ICoinApi = CoinApiImpl(apiService)

    @Provides
    @Singleton
    fun provideCoinRepository(coinApi: ICoinApi): ICoinRepository =
        CoinRepositoryImpl(coinApi)

    @Provides
    @Singleton
    fun provideGetCoinListUseCase(coinRepository: ICoinRepository): GetCoinListUseCase =
        GetCoinListUseCase(coinRepository)

    @Provides
    @Singleton
    fun provideGetCoinDetailUseCase(coinRepository: ICoinRepository): GetCoinDetailUseCase =
        GetCoinDetailUseCase(coinRepository)
}