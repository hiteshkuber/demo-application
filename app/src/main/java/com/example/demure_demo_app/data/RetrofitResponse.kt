package com.example.demure_demo_app.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitResponse {

    const val baseUrl = "https://testa2.aisle.co/V1/"
    const val numberKey = "number"
    const val otpKey = "otp"

    private fun createRetrofitInstance() =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getJsonPlaceHolderApi(): AisleApi = createRetrofitInstance().create(AisleApi::class.java)
}