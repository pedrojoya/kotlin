package es.iessaladillo.pedrojoya.pr212.data.local.model

import android.content.ContentValues
import android.database.Cursor
import androidx.core.content.contentValuesOf
import androidx.core.database.getStringOrNull
import es.iessaladillo.pedrojoya.pr212.data.local.DbContract

data class Student(
        var id: Long = 0,
        var name: String,
        var phone: String,
        var grade: String,
        var address: String? = null) {

    constructor(cursor: Cursor) : this(
            cursor.getLong(cursor.getColumnIndex(DbContract.Student._ID)),
            cursor.getString(cursor.getColumnIndex(DbContract.Student.NAME)),
            cursor.getString(cursor.getColumnIndex(DbContract.Student.PHONE)),
            cursor.getString(cursor.getColumnIndex(DbContract.Student.GRADE)),
            cursor.getStringOrNull(cursor.getColumnIndex(DbContract.Student.ADDRESS)))

    fun toContentValues(): ContentValues =
            contentValuesOf(
                    DbContract.Student.NAME to name,
                    DbContract.Student.GRADE to grade,
                    DbContract.Student.PHONE to phone,
                    DbContract.Student.ADDRESS to address
            )

}
