package com.capstone.fokusin.data.service

import android.provider.ContactsContract.CommonDataKinds.Email
import com.capstone.fokusin.data.response.FokPredicResponse
import com.capstone.fokusin.data.response.LoginResponse
import com.capstone.fokusin.data.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface FokService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @Multipart
    @POST("predictions")
    fun upload(
        @Header("Authorization") auth: String,
        @Part file: MultipartBody.Part
    ): Call<FokPredicResponse>

    @FormUrlEncoded
    @PUT("users/{id}")
    fun getUser(
        @Header("Authorization") auth: String,
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password") pass: String?,
        @Path("id") id: String
    ): Call<LoginResponse>
}