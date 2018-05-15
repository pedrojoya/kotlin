package es.iessaladillo.pedrojoya.pr212.data.local

import android.provider.BaseColumns

class DbContract private constructor() {

    companion object {
        const val DB_NAME = "school"
        const val DB_VERSION = 1
    }

    // Students table.
    class Student private constructor() {
        companion object {
            const val TABLE_NAME = "students"
            @Suppress("ObjectPropertyName")
            const val _ID = "_id"
            @Suppress("ObjectPropertyName")
            const val _COUNT = "_count"
            const val NAME = "name"
            const val GRADE = "grade"
            const val PHONE = "phone"
            const val ADDRESS = "address"
            val ALL_FIELDS = arrayOf(BaseColumns._ID, NAME, GRADE, PHONE, ADDRESS)
            const val NOTIFICATION_URI = "content://es.iessaladillo.school/students"
            const val DROP_TABLE_QUERY = "drop table if exists $TABLE_NAME"
        }
    }

}
