package com.nikhil.mybestfriend.feature.commons.data.api.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptor
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatBreed
import com.nikhil.mybestfriend.feature.cat.data.api.response.CatDetails
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "17d94b92-754f-46eb-99a0-65be65b5d18f"
const val BASE_URL = "https://api.thecatapi.com/v1/"



interface CatAPIService {

    @GET("breeds")
    fun getCatBreedAsync(
        @Query("limit") limit: Int = 10 ,
        @Query("page") page: Int = 0
    ): Deferred<List<CatBreed>>


    @GET("images/search")
    fun getCatDetailsAsync
                (
        @Query("breed_ids") breedIds: String,
        @Query("include_breeds") includeBreeds: Boolean = true

    ): Deferred<List<CatDetails>>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor
        ): CatAPIService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("x-api-key",
                        API_KEY
                    )
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatAPIService::class.java)
        }
    }
}

