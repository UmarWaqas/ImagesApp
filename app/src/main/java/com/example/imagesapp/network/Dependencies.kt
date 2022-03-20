package com.appsologix.blackbuildup.network

import android.util.Log
import com.example.imagesapp.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

abstract class Dependencies
{

    protected fun <S> provideRestApi(classService: Class<S>, apiBaseUrl: String?): S {
        var baseUrl = apiBaseUrl
        val okHttpClient = provideOkHttpClientDefault(provideHttpLoggingInterceptor())
        //  val okHttpClient = provideOkHttpClientDefault(provideHttpLoggingInterceptor())
        // val okHttpClient= OkHttpClient().networkInterceptors().add(object :StethoInterceptor())
//**

        if (baseUrl == null)
            baseUrl = getBaseUrl()
        val builder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create().asLenient().withNullSerialization())


        return builder.build().create(classService)
    }

    protected fun provideOkHttpClientDefault(interceptor:HttpLoggingInterceptor): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
      //  okBuilder.addInterceptor(ConnectivityInterceptor())
        okBuilder.addInterceptor(interceptor)
        okBuilder.addNetworkInterceptor(StethoInterceptor())
        okBuilder.addInterceptor { chain ->

            var request = chain.request()
            val url = request.url().newBuilder().build()

            val builder = request.newBuilder().url(url)
            val headers = getHeaders()
            if ( headers.size > 0) {
                for ((key, value) in headers) {
                    builder.addHeader(key, value)
                    Log.e(key, value)
                }
            }
            chain.proceed(builder.build())
        }
        var timeout = getTimeOut()
//        if (!NetworkUtils.isInternetConection()) {     // Check if there is internet connection
//          timeout=getZeroTimeOut()
//        }

        okBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)


        return okBuilder.build()
    }

    protected fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }





    fun getTimeOut(): Int {
        return 30
    }

    fun getZeroTimeOut():Int {
        return 1
    }


    abstract fun getBaseUrl(): String

    abstract fun getHeaders(): HashMap<String, String>


}