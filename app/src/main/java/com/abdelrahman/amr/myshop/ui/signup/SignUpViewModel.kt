package com.abdelrahman.amr.myshop.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelrahman.amr.myshop.models.BaseResponse
import com.abdelrahman.amr.myshop.models.user.User
import com.abdelrahman.amr.myshop.models.user.UserResponse
import com.abdelrahman.amr.myshop.utils.Resource
import kotlinx.coroutines.launch

class SignUpViewModel:ViewModel() {
    private var signUpRepository = SignUpRepository()
    var userFlow:MutableLiveData<Resource<BaseResponse<UserResponse>>> =signUpRepository.userFlow
       fun registerUser(user: User){
           viewModelScope.launch{
            signUpRepository.registration(user)
           }
     }


}