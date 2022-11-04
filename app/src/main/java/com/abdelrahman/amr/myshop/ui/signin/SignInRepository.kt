package com.abdelrahman.amr.myshop.ui.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abdelrahman.amr.myshop.api.RetrofitInstance
import com.abdelrahman.amr.myshop.db.PreferenceManager
import com.abdelrahman.amr.myshop.models.BaseResponse
import com.abdelrahman.amr.myshop.models.user.UserResponse
import com.abdelrahman.amr.myshop.utils.Constants.Companion.IS_LOGIN
import com.abdelrahman.amr.myshop.utils.MyShopApplication
import com.abdelrahman.amr.myshop.utils.Resource

class SignInRepository {
    var userLogin:MutableLiveData<Resource<BaseResponse<UserResponse>>> = MutableLiveData<Resource<BaseResponse<UserResponse>>>()
    suspend fun loginUser(email:String, password:String){
        userLogin.postValue(Resource.Loading())
        val response =  RetrofitInstance.api.userLogin(email,password)
        if (response.isSuccessful){
            if (response.body()!!.data !=null){
                userLogin.postValue(Resource.Success(response.body()!!))
                 val preference = PreferenceManager(MyShopApplication().getInstance())
                preference.logIn(response.body()!!.data!!)
                preference.putBoolean(IS_LOGIN,true)


            }
            else{
                userLogin.postValue(Resource.Error(response.body()!!.message!!))
            }
        }
        else{
            Log.d("error",response.message())
        }

    }

}