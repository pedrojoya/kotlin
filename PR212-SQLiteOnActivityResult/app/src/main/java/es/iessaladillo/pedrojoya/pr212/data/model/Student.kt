package es.iessaladillo.pedrojoya.pr212.data.model

import android.content.ContentValues
import android.database.Cursor
import androidx.core.content.contentValuesOf
import androidx.core.database.getLong
import androidx.core.database.getString
import androidx.core.database.getStringOrNull
import es.iessaladillo.pedrojoya.pr212.data.local.DbContract

data class Student(
        var id: Long = 0,
        var name: String,
        var phone: String,
        var grade: String,
        var address: String? = null) {

    constructor(cursor: Cursor) : this(
            cursor.getLong(DbContract.Student._ID),
            cursor.getString(DbContract.Student.NAME),
            cursor.getString(DbContract.Student.PHONE),
            cursor.getString(DbContract.Student.GRADE),
            cursor.getStringOrNull(DbContract.Student.ADDRESS))

    fun toContentValues(): ContentValues =
            contentValuesOf(
                    DbContract.Student.NAME to name,
                    DbContract.Student.GRADE to grade,
                    DbContract.Student.PHONE to phone,
                    DbContract.Student.ADDRESS to address
            )

}
