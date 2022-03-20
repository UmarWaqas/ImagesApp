package com.appsologix.blackbuildup.network


//class ConnectivityInterceptor() : Interceptor {
//
//
////    private val isConnected: Boolean
////        get() {
////            val cm = MyApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
////            val activeNetwork = cm.activeNetworkInfo
////            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
////        }
//
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalRequest = chain.request()
//       // try{
//
//        if (!isConnected) {
//            throw NoNetworkException()
//        }
////        }catch (e:Exception) {
////        e.printStackTrace()
////        }
//        return chain.proceed(originalRequest)
//    }
//
//    class NoNetworkException internal constructor() : IOException("netowrk Issue ")
//}