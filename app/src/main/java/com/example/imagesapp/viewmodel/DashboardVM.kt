package com.appsologix.verepass.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.appsologix.verepass.repositories.ImagesRepository
import com.example.imagesapp.models.ImagesResponse

class DashboardVM : ViewModel() {
    private val TAG: String = "DashboardVM"

    val dataSource: MutableLiveData<String> = MutableLiveData()

    val imagesRequest: MutableLiveData<String> = MutableLiveData()

    val imagesResponse:
            LiveData<ImagesResponse> = Transformations.switchMap(imagesRequest)
    {
        imagesRequest.value?.let { request ->
            //GET online images....
            ImagesRepository.getOnlineImages()

            //GET offline images.....

        }
    }

    fun getImages(source:String) {

        source?.let { imageSource ->
            imagesRequest.value = imageSource
        }

    }//end of method getImages....

}//end of class DashboardVM....