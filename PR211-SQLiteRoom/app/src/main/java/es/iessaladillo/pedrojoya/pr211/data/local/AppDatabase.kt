package es.iessaladillo.pedrojoya.pr211.data.local

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.iessaladillo.pedrojoya.pr211.base.SingletonHolder
import es.iessaladillo.pedrojoya.pr211.data.local.AppDatabase.Companion.buildAppDatabase
import es.iessaladillo.pedrojoya.pr211.data.local.model.Student

const val DATABASE_NAME = "instituto.db"

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object : SingletonHolder<AppDatabase, Context>({ context -> buildAppDatabase(context) }) {

        private fun buildAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder<AppDatabase>(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(
                            object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    insertInitialData()
                                }
                            })
                    .build()

        private fun insertInitialData() {
            AsyncTask.THREAD_POOL_EXECUTOR.execute {
                instance?.studentDao()?.insert(
                        Student(0, "Baldomero", "666666666", "2º CFGS DAM", "La casa de Baldo"))
            }

        }


    }

}

