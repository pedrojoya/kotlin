package es.iessaladillo.pedrojoya.pr095.data.model

import android.os.Environment
import es.iessaladillo.pedrojoya.pr095.ui.main.MP3_FILE_EXTENSION
import java.io.File

data class Song(val name: String, val duration: String, val author: String, val url: String) {

    val publicFile: File?
        get() {
            val directory = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MUSIC)
            val file = File(directory, name + MP3_FILE_EXTENSION)
            return if (file.exists()) file else null
        }

}
