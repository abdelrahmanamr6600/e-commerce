package com.abdelrahman.amr.myshop.ui.signup

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abdelrahman.amr.myshop.api.RetrofitInstance
import com.abdelrahman.amr.myshop.db.PreferenceManager
import com.abdelrahman.amr.myshop.models.BaseResponse
import com.abdelrahman.amr.myshop.models.user.User
import com.abdelrahman.amr.myshop.models.user.UserResponse
import com.abdelrahman.amr.myshop.utils.Constants.Companion.IS_LOGIN
import com.abdelrahman.amr.myshop.utils.MyShopApplication
import com.abdelrahman.amr.myshop.utils.Resource
import kotlinx.coroutines.delay

class SignUpRepository {
    var userFlow:MutableLiveData<Resource<BaseResponse<UserResponse>>> = MutableLiveData<Resource<BaseResponse<UserResponse>>>()

     suspend fun registration(user:User):MutableLiveData<Resource<BaseResponse<UserResponse>>>{
         userFlow.postValue(Resource.Loading())
        val response =  RetrofitInstance.api.registerUser(user)
         if(response.isSuccessful) {
             if (response.body()!!.data != null) {
                 userFlow.postValue(Resource.Success(response.body()!!))
             } else {
                 userFlow.postValue(Resource.Error(response.body()!!.message!!))

             }
         }
        else{
             userFlow.postValue(Resource.Error(response.message()))
        }

        return userFlow
    }





}