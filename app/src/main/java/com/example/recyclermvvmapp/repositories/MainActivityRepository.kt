package com.example.recyclermvvmapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclermvvmapp.models.RequestResponse
import com.example.recyclermvvmapp.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivityRepository {

    private val SERVICE_BASE_URL = "https://dl.dropboxusercontent.com/"

    private var apiService: ApiService? = null
    private var responseLiveData: MutableLiveData<RequestResponse>? = null
    private var failureLiveData: MutableLiveData<Throwable>? = null

    init {
        responseLiveData = MutableLiveData<RequestResponse>()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        apiService = Retrofit.Builder()
            .baseUrl(SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    /**
     * Invoke Web Api and updates Live data for success and failure
     */
    fun getItems() {
        apiService?.getItems()
            ?.enqueue(object : Callback<RequestResponse?> {
                override fun onResponse(
                    call: Call<RequestResponse?>,
                    response: Response<RequestResponse?>
                ) {
                    if (response.body() != null) {
                        responseLiveData?.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<RequestResponse?>, t: Throwable) {
                    responseLiveData?.postValue(null)
                    failureLiveData?.postValue(t)
                }

            })
    }

    fun getResponseLiveData(): LiveData<RequestResponse?>? {
        return responseLiveData
    }

    fun getFailedLiveData(): LiveData<Throwable>? {
        return failureLiveData
    }
}