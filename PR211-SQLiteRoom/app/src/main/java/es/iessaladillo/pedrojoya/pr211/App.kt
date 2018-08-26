package es.iessaladillo.pedrojoya.pr211

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

import com.facebook.stetho.Stetho
import es.iessaladillo.pedrojoya.pr211.data.local.AppDatabase
import es.iessaladillo.pedrojoya.pr211.data.local.DATABASE_NAME
import es.iessaladillo.pedrojoya.pr211.utils.DatabaseUtils

@Suppress("unused")
class App : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        // Gets or creates the database.
        App.database = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        DatabaseUtils.executeSqlFromAssetsFile(db, db.version, applicationContext.assets)
                    }
                }).build()
    }

}