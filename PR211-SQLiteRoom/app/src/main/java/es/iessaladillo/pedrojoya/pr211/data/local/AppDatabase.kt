package es.iessaladillo.pedrojoya.pr211.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.pr211.data.model.Student

const val DATABASE_NAME = "instituto.db"

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

}

