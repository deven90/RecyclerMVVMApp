package com.example.recyclermvvmapp.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

/**
 * Interceptor Which will throw Custom Exception In No Internet Condition
 * Uses Connection Detector class for checking internet.
 */
class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val mContext: Context = context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            throw NoConnectivityException()
            // Throwing our custom exception 'NoConnectivityException'
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private val isConnected: Boolean
        get() {
            return ConnectionDetector.isInternetAvailable(mContext)
        }

}