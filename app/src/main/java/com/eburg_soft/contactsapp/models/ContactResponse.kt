package com.eburg_soft.contactsapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactRes(
    val id: String,
    val name: String,
    val phone: String,
    val height: Double,
    val biography: String,
    val temperament: String,
    val contactEducationPeriodRes: ContactEducationPeriodRes
) : Parcelable

@Parcelize
data class ContactEducationPeriodRes(
    val start: String,
    val end: String
) : Parcelable