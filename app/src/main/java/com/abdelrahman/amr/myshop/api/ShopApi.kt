package com.abdelrahman.amr.myshop.api

import com.abdelrahman.amr.myshop.models.BaseResponse
import com.abdelrahman.amr.myshop.models.user.User
import com.abdelrahman.amr.myshop.models.user.UserResponse

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ShopApi {

    @POST("register")
    suspend fun registerUser(@Body user:User):Response<User>
}