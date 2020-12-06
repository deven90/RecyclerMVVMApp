package com.example.recyclermvvmapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recyclermvvmapp.models.RequestResponse
import com.example.recyclermvvmapp.repositories.MainActivityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var mainActivityRepository: MainActivityRepository
    private var responseLiveData: LiveData<RequestResponse?>? = null
    private var failureLiveData: LiveData<Throwable>? = null

    fun init() {
        mainActivityRepository = MainActivityRepository()
        responseLiveData = mainActivityRepository.getResponseLiveData()
        failureLiveData = mainActivityRepository.getFailedLiveData()
    }


    /**
     * To check if response already available or not
     */
    fun hasResponse(): Boolean {
        responseLiveData?.let {
            if (it.value != null && it.value!!.rows.isNotEmpty()) {
                return true
            }
        }
        return false
    }

    /**
     * Returns Response Live Data
     */
    fun getResponse(): LiveData<RequestResponse?>? {
        return responseLiveData
    }

    /**
     * Returns failure Live Data
     */
    fun getFailureData(): LiveData<Throwable>? {
        return failureLiveData
    }

    /**
     * Calls Api from repository
     */
    fun getItems() {
        mainActivityRepository.getItems()
    }

}