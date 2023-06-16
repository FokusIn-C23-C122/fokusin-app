package com.capstone.fokusin.data.service

import android.provider.ContactsContract.CommonDataKinds.Email
import com.capstone.fokusin.data.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface FokService {

    @FormUrlEncoded
    @POST("api/user/login/")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginRess>

    @FormUrlEncoded
    @POST("api/user/register/")
    suspend fun register(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @Multipart
    @POST("api/analysis")
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