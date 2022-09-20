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
import com.abdelrahman.amr.myshop.utils.MyShopApplication
import com.abdelrahman.amr.myshop.utils.Resource

class SignUpRepository {
    var userFlow:MutableLiveData<Resource<User>> = MutableLiveData<Resource<User>>()

     suspend fun registration(user:User):MutableLiveData<Resource<User>>{
         userFlow.postValue(Resource.Loading())
        val response =  RetrofitInstance.api.registerUser(user)
         if(response.isSuccessful)
        {

             userFlow.postValue(Resource.Success(response.body()!!))
        }
        else{
             userFlow.postValue(Resource.Error(response.message()))
        }

        return userFlow
    }





}