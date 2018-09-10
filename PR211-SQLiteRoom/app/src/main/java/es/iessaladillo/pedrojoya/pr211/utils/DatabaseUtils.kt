package es.iessaladillo.pedrojoya.pr211.utils

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.text.TextUtils
import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object DatabaseUtils {

    // Execute SQL sentences from version assets file into database.
    // Version assets file must be name version.sql, for example 0.sql
    fun executeSqlFromAssetsFile(db: SupportSQLiteDatabase, version: Int,
                                 assetManager: AssetManager) {
        var reader: BufferedReader? = null
        try {
            @SuppressLint("DefaultLocale")
            val filename = String.format("%d.sql", version)
            val inputStream = assetManager.open(filename)
            reader = BufferedReader(InputStreamReader(inputStream))
            val statement = StringBuilder()
            var line = reader.readLine()
            while (line != null) {
                // Ignore empty lines and SQL comments.
                if (!TextUtils.isEmpty(line) && !line.startsWith("--")) {
                    statement.append(line.trim { it <= ' ' })
                }
                // If valid SQL sentence.
                if (line.endsWith(";")) {
                    db.execSQL(statement.toString())
                    statement.setLength(0)
                }
                line = reader.readLine()
            }
        } catch (e: Exception) {
            Log.e("DatabaseUtils", "Error reading SQL file", e)
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    Log.w("DatabaseUtils", "Error closing file reader", e)
                }

            }
        }
    }

}
