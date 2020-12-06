package com.example.recyclermvvmapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclermvvmapp.models.RequestResponse
import com.example.recyclermvvmapp.repositories.MainActivityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val mainActivityRepository: MainActivityRepository = MainActivityRepository()
    private var responseLiveData: LiveData<RequestResponse?>? = null
    private var failureLiveData: LiveData<Throwable>? = null

    init {
        responseLiveData = mainActivityRepository.getResponseLiveData()
        failureLiveData = mainActivityRepository.getFailedLiveData()
        mainActivityRepository.getItems()
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

    fun getIsRefreshing(): MutableLiveData<Boolean> {
        return mainActivityRepository.getRefreshing()
    }

    fun getTitle(): MutableLiveData<String> {
        return mainActivityRepository.getTitle()
    }
}