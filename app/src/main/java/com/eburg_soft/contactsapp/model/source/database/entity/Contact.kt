package com.eburg_soft.contactsapp.model.source.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eburg_soft.contactsapp.model.source.database.entity.Contact.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class Contact(
    @ColumnInfo(name = COLUMN_ID) @PrimaryKey(autoGenerate = false) var contactId: Long = 0,
    @ColumnInfo(name = COLUMN_NAME) var contactName: String = "",
    @ColumnInfo(name = COLUMN_PHONE) var contactPhone: String = "",
    @ColumnInfo(name = COLUMN_HEIGHT) var contactHeingt: String = "",
    @ColumnInfo(name = COLUMN_BIOGRAPHY) var contactBiography: String = "",
    @ColumnInfo(name = COLUMN_TEMPERAMENT) var contactTemperament: String = "",
    @ColumnInfo(name = COLUMN_EDUCATION_START) var contactEducationStart: String = "",
    @ColumnInfo(name = COLUMN_EDUCATION_END) var contactEducationEnd: String = ""
) : Parcelable {

    companion object {
        const val TABLE_NAME = "Contact"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_HEIGHT = "height"
        const val COLUMN_BIOGRAPHY = "biography"
        const val COLUMN_TEMPERAMENT = "temperament"
        const val COLUMN_EDUCATION_START = "education_start"
        const val COLUMN_EDUCATION_END = "education_end"
    }
}