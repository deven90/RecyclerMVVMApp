package com.example.recyclermvvmapp.network

import okio.IOException

class NoConnectivityException : IOException() {
    override val message: String = "No Internet Connection"
}