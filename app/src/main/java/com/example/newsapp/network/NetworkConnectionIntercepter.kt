package com.example.newsapp.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.newsapp.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


/**
 * This class is custom okhttp interceptor class for retrofit
 * we will add this interceptor while building retrofit instance
 * each connection will be intercepted and  throws{@link NoInternetException},
 * if internet not available
 * @see NetworkModule
 */
class NetworkConnectionIntercepter(context : Context) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable()) {
            throw NoInternetException("Make sure network is active")
        }

        var request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter("apiKey", "1c492daaf9554c5e81d4d66c4b216d8f")
            .build()
        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }

    private fun isInternetAvailable() : Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}