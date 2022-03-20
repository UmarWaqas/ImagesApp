package com.appsologix.verepass.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.appsologix.blackbuildup.network.ApiClient
import com.example.imagesapp.models.ImagesResponse
import com.google.gson.Gson
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ImagesRepository {

    var job: CompletableJob? = null


    fun getImages(source: String) {

        if (source.equals("online")) {
             getOnlineImages()
        }
        else  if (source.equals("offline")){
         getOnlineImages()

        }


    }//end of method getImages....

    fun getOnlineImages(): LiveData<ImagesResponse> {

        job = Job()
        return object :LiveData<ImagesResponse>() {
            override fun onActive() {
                super.onActive()
                job?.let { currentJob ->
                    CoroutineScope(IO + currentJob).launch {
                        var call = ApiClient().getServerAPI().getImages()
                        call.enqueue(object : Callback<ImagesResponse> {
                            override fun onResponse(
                                call: Call<ImagesResponse>,
                                response: Response<ImagesResponse>
                            ) {

                                CoroutineScope(Main).launch {
                                    if (response.isSuccessful) {
                                        Log.e("ERROR_RESPONSE", "Successfull")
                                        value = response.body()
                                    } else {
                                        Log.e("ERROR_RESPONSE", "Unsuccessfull")
                                        if (response.errorBody() != null) {
                                            val sr: String =
                                                response.errorBody()?.string().toString()
                                            Log.e("ERROR_RESPONSE", sr)
                                            //val type = object : TypeToken<RegisterErrors>() {}.type
                                            val error: ImagesResponse = Gson()
                                                .fromJson(sr, ImagesResponse::class.java)
                                            value = error
                                        }
                                    }
                                    currentJob.complete()

                                }//end of coroutine main....

                            }//end of method onResponse....

                            override fun onFailure(call: Call<ImagesResponse>, t: Throwable) {
                                t.printStackTrace()
                                currentJob.cancel()

                            }//end of method onFailure....
                        })

                    }//end of job coroutine....
                }

            }//end of method onActive....
        }

    }//end of method getOnlineImages....

    fun getOfflineImages(){



    }//end of method getOfflineImages....



    fun cancelJobs() {
        job?.cancel()
    }//end of method cancelJobs....

}//end of class AuthRepository....