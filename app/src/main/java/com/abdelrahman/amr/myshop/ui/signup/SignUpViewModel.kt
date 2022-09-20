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
    var userFlow:MutableLiveData<Resource<User>> = MutableLiveData<Resource<User>>()
    private var signUpRepository = SignUpRepository()
       fun registerUser(user: User){
           viewModelScope.launch{

             userFlow =   signUpRepository.registration(user)
           }
     }


}