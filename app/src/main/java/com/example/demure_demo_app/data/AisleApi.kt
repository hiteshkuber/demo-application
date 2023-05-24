package com.example.demure_demo_app.data

import com.example.demure_demo_app.data.profile_data.Feed
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AisleApi {

    @POST("users/phone_number_login")
    fun phoneNumberLogin(@Body jsonObject: JsonObject): Call<ResponseBody>

    @POST("users/verify_otp")
    fun verifyOtp(@Body jsonObject:JsonObject): Call<ResponseBody>

    @GET("users/test_profile_list")
    fun testProfileList(@Header("Authorization") Authorization:String): Call<Feed>
}