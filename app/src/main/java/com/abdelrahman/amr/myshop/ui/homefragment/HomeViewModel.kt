package com.abdelrahman.amr.myshop.ui.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdelrahman.amr.myshop.models.home.HomeResponse
import com.abdelrahman.amr.myshop.ui.homefragment.HomeRepository
import com.abdelrahman.amr.myshop.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private var homeRepository = HomeRepository()
    var dataFlow: MutableLiveData<Resource<HomeResponse>> =homeRepository.homeLiveData

    fun getHomeData(){
        viewModelScope.launch {
            homeRepository.getHomeData()
        }

    }




}