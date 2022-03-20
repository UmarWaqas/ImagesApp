package com.appsologix.blackbuildup.network



import com.example.imagesapp.models.ImagesResponse
import com.example.imagesapp.utilities.Constants

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ResponseListener {

    @Streaming
    @GET
    fun downloadImage(@Url fileUrl: String?): Call<ResponseBody>

    @GET(Constants.IMAGES_URL)
    fun getImages(): Call<ImagesResponse>



}