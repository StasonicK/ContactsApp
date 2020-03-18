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
    @ColumnInfo(name = COLUMN_ID) @PrimaryKey(autoGenerate = false) val contactId: Long = 0,
    @ColumnInfo(name = COLUMN_NAME) val contactName: String = "",
    @ColumnInfo(name = COLUMN_PHONE) val contactPhone: String = "",
    @ColumnInfo(name = COLUMN_HEIGHT) val contactHeingt: String = "",
    @ColumnInfo(name = COLUMN_BIOGRAPHY) val contactBiography: String = "",
    @ColumnInfo(name = COLUMN_TEMPERAMENT) val contactTemperament: String = "",
    @ColumnInfo(name = COLUMN_EDUCATION_START) val contactEducationStart: String = "",
    @ColumnInfo(name = COLUMN_EDUCATION_END) val contactEducationEnd: String = ""
): Parcelable {

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