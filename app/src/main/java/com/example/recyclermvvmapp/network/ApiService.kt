package com.example.recyclermvvmapp.network

import com.example.recyclermvvmapp.models.RequestResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * All Services defined here
 */
interface ApiService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getItems(): Call<RequestResponse?>
}