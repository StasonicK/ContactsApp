package com.eburg_soft.contactsapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactRes(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("height") val height: Double,
    @SerializedName("biography") val biography: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("educationPeriod") val contactEducationPeriodRes: ContactEducationPeriodRes
) : Parcelable

@Parcelize
data class ContactEducationPeriodRes(
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String
) : Parcelable