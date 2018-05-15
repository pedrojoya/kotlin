@file:JvmName("DatabaseUtils")

package es.iessaladillo.pedrojoya.pr212.utils

import android.content.res.AssetManager
import android.database.sqlite.SQLiteDatabase
import android.text.TextUtils
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

// Execute SQL sentences from version assets file into database.
// Version assets file must be name version.sql, for example 1.sql
fun executeSqlFromAssetsFile(db: SQLiteDatabase, version: Int,
                             assetManager: AssetManager) {
    var reader: BufferedReader? = null
    try {
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
    } catch (e: IOException) {
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
