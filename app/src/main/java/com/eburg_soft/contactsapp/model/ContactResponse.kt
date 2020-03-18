package com.eburg_soft.contactsapp.model

import com.google.gson.annotations.SerializedName

data class Contact (

    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("height") val height : Double,
    @SerializedName("biography") val biography : String,
    @SerializedName("temperament") val temperament : String,
    @SerializedName("educationPeriod") val contactEducationPeriod : ContactEducationPeriod
)

data class ContactEducationPeriod (

    @SerializedName("start") val start : String,
    @SerializedName("end") val end : String
)