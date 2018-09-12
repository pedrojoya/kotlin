package es.iessaladillo.pedrojoya.pr212.data.local

import android.annotation.TargetApi
import android.content.Context
import android.content.res.AssetManager
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import es.iessaladillo.pedrojoya.pr212.base.SingletonHolder
import es.iessaladillo.pedrojoya.pr212.utils.executeSqlFromAssetsFile

class DbHelper private constructor(context: Context) : SQLiteOpenHelper(context.applicationContext, DbContract.DB_NAME,
        null, DbContract.DB_VERSION) {

    private val assetManager: AssetManager = context.assets

    // Called by the framewrok on API >= 16 to configure database, after creating database file
    // and opening database, and before calling onCreate.
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        // Enable database log.
        setWriteAheadLoggingEnabled(true)
        // Enable foreign keys in SQLite.
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        executeSqlFromAssetsFile(db, DbContract.DB_VERSION, assetManager)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop tables (NOT IN PRODUCTION!!!).
        db.execSQL(DbContract.Student.DROP_TABLE_QUERY)
        executeSqlFromAssetsFile(db, DbContract.DB_VERSION, assetManager)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop tables (NOT IN PRODUCTION!!!).
        db.execSQL(DbContract.Student.DROP_TABLE_QUERY)
        executeSqlFromAssetsFile(db, DbContract.DB_VERSION, assetManager)
    }

    fun <T> use(action: SQLiteDatabase.() -> T): T {
        with (writableDatabase) {
            val result = action()
            close()
            return result
        }
    }

    companion object: SingletonHolder<DbHelper, Context>(::DbHelper)

}
