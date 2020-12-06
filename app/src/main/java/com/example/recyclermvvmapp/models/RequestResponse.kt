package com.example.recyclermvvmapp.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Represents response getting from get api request
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class RequestResponse(
    @SerializedName("rows")
    var rows: MutableList<Row>,
    @SerializedName("title")
    var title: String
) : Parcelable