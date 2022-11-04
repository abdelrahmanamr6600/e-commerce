package com.abdelrahman.amr.myshop.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelrahman.amr.myshop.models.BaseResponse
import com.abdelrahman.amr.myshop.models.user.UserResponse
import com.abdelrahman.amr.myshop.utils.Resource
import kotlinx.coroutines.launch

class SignInViewModel:ViewModel() {
    var signInRepository = SignInRepository()
    var userLogin: MutableLiveData<Resource<BaseResponse<UserResponse>>> = signInRepository.userLogin

    suspend fun userLogin(email:String , password:String) =
        viewModelScope.launch {
            signInRepository.loginUser(email,password)
        }
}