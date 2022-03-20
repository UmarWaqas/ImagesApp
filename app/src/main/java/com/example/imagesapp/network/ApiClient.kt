package com.appsologix.blackbuildup.network


import com.example.imagesapp.utilities.Constants
import java.util.*

class ApiClient: Dependencies() {

    /*
    static variable
    */

    companion object {
        private lateinit var serverAPI: ResponseListener
        private var token:String?=null
          val newApiClientInstance= ApiClient()
    }

    /*
        return server api instance
    */


    fun getServerAPI(): ResponseListener {
        serverAPI = provideRestApi(ResponseListener::class.java, null)
        return serverAPI
    }

    fun getServerAPI(accessToken:String): ResponseListener {
        serverAPI = provideRestApi(ResponseListener::class.java, null)
        token =accessToken
        return serverAPI
    }

    /*
    Base URL Initialization
    */

    override fun getBaseUrl(): String {

        return Constants.BASE_URL
    }

    /*
    Header Initialization
    */
    override fun getHeaders(): HashMap<String, String> {
        val params = HashMap<String, String>()
        params.put("accept", "application/json")
        token?.let {
                accessToken->  params.put("Authorization", "Bearer "+ accessToken)
        }

        return params
    }

}