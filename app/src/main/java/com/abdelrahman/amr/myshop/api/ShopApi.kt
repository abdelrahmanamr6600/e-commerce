package com.abdelrahman.amr.myshop.api

import com.abdelrahman.amr.myshop.models.BaseResponse
import com.abdelrahman.amr.myshop.models.home.HomeResponse

import com.abdelrahman.amr.myshop.models.user.User
import com.abdelrahman.amr.myshop.models.user.UserResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ShopApi {

    @POST("register")
    suspend fun registerUser(@Body user:User):Response<BaseResponse<UserResponse>>
   @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(@Field("email") email:String , @Field("password") password:String):Response<BaseResponse<UserResponse>>

    @GET("home")
    suspend fun getHomeData():Response<HomeResponse>
}