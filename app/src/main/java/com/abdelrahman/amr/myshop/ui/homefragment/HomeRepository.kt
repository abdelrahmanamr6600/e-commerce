package com.abdelrahman.amr.myshop.ui.homefragment

import androidx.lifecycle.MutableLiveData
import com.abdelrahman.amr.myshop.api.RetrofitInstance
import com.abdelrahman.amr.myshop.models.home.HomeResponse
import com.abdelrahman.amr.myshop.utils.Resource

class HomeRepository {
    var homeLiveData:MutableLiveData<Resource<HomeResponse>> = MutableLiveData()

    suspend fun getHomeData(){
        val response = RetrofitInstance.api.getHomeData()
        if (response.isSuccessful){
            if (response.body()!!.data!=null){
                homeLiveData.postValue(Resource.Success(response.body()!!))
            }
            else{
                homeLiveData.postValue(Resource.Error(response.body()!!.message!!))
            }

        }
        else{
            homeLiveData.postValue(Resource.Error(response.message()))
        }
    }
}