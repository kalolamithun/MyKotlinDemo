package com.md.mykotlin.ui.auth.api

import com.md.mykotlin.ui.auth.model.request.LoginRequestModel
import com.md.mykotlin.ui.auth.model.response.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkInterceptor {

    /**
     * login api
     *
     * {
     *    "deviceToken": "<DEVICE TOKEN>",
     *    "deviceType": "<PLATFORM>",
     *    "emailId": "<EMAIL ADDRESS>",
     *    "password": "<PASSWORD>"
     * }
     */
    @POST("v1/auth/login")
    fun callLoginApi(@Body requestBody: LoginRequestModel): Call<UserModel>

    /**
     * login api
     *
     * {
     * "method": "<API METHOD>",
     * "platform": "<PLATFORM>",
     * "data": {
     *    "deviceToken": "<DEVICE TOKEN>",
     *    "deviceType": "<PLATFORM>",
     *    "emailId": "<EMAIL ADDRESS>",
     *    "password": "<PASSWORD>"
     *   }
     * }
     */
    @POST("v1/auth/login")
    fun callLoginApi1(@Body requestBody: LoginRequestModel): Call<UserModel>
}