package es.iessaladillo.pedrojoya.pr212.data.local

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import es.iessaladillo.pedrojoya.pr212.data.local.model.Student

@Suppress("unused")
class StudentDao(context: Context, private val dbHelper: DbHelper) {

    private val contentResolver: ContentResolver = context.applicationContext.contentResolver

    fun openWritableDatabase(): SQLiteDatabase {
        return dbHelper.writableDatabase
    }

    fun closeDatabase() {
        dbHelper.close()
    }

    // CRUD (Create-Read-Update-Delete) de la tabla alumnos

    // Returns student _id or -1 in of error.
    fun createStudent(student: Student): Long =
        dbHelper.use {
            val id = insert(DbContract.Student.TABLE_NAME, null, student.toContentValues())
            contentResolver.notifyChange(Uri.parse(DbContract.Student.NOTIFICATION_URI), null)
            id
        }

    // Return true in case of success or false otherwise.
    fun deleteStudent(id: Long): Boolean =
            dbHelper.use {
                val count = delete(DbContract.Student.TABLE_NAME,
                        DbContract.Student._ID + " = " + id, null).toLong()
                contentResolver.notifyChange(Uri.parse(DbContract.Student.NOTIFICATION_URI), null)
                count == 1L
            }

    // Return true in case of success or false otherwise.
    fun updateStudent(student: Student): Boolean =
            dbHelper.use {
                val count = update(DbContract.Student.TABLE_NAME, student.toContentValues(),
                        DbContract.Student._ID + " = " + student.id, null).toLong()
                contentResolver.notifyChange(Uri.parse(DbContract.Student.NOTIFICATION_URI), null)
                count == 1L
            }

    // Return student or null if it doesn't exist.
    fun getStudent(id: Long): Student? =
            dbHelper.use {
                val cursor = query(true, DbContract.Student.TABLE_NAME, DbContract.Student.ALL_FIELDS,
                        DbContract.Student._ID + " = " + id,
                        null, null, null, null, null)
                val student = if (cursor.moveToFirst()) Student(cursor) else null
                cursor.close()
                student
            }

    // Return all students query cursor, ordered alphabetically.
    fun queryStudents(): Cursor =
        // WE DON'T CLOSE DATABASE SO WE CAN OPERATE WITH THE CURSOR.
        dbHelper.writableDatabase
                .query(DbContract.Student.TABLE_NAME, DbContract.Student.ALL_FIELDS,
                        null, null, null, null, DbContract.Student.NAME)

    // Return all students, ordered alphabetically.
    fun getStudents(): List<Student> =
        dbHelper.use {
            val cursor = query(DbContract.Student.TABLE_NAME, DbContract.Student.ALL_FIELDS,
                    null, null, null, null, DbContract.Student.NAME)
            val result = generateSequence { if (cursor.moveToNext()) cursor else null }
                    .map { Student(it) }
                    .toList()
            cursor.close()
            result
        }

}
