package com.example.recyclermvvmapp.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Represents single Object from Rows from Request response.
 * It has three attributes title, description & image
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Row(
    @SerializedName("description")
    var description: String?,
    @SerializedName("imageHref")
    var imageHref: String?,
    @SerializedName("title")
    var title: String?
) : Parcelable