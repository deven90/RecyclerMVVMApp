package com.example.recyclermvvmapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclermvvmapp.models.RequestResponse
import com.example.recyclermvvmapp.repositories.MainActivityRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val mainActivityRepository: MainActivityRepository = MainActivityRepository(application)
    private var responseLiveData: LiveData<RequestResponse?>? = null

    init {
        responseLiveData = mainActivityRepository.getResponseLiveData()
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
    fun getFailureData(): MutableLiveData<Throwable>? {
        return mainActivityRepository.getFailedLiveData()
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