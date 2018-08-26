package es.iessaladillo.pedrojoya.pr040.utils

import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {

    @Throws(IOException::class)
    fun readContent(inputStream: InputStream): String =
        inputStream.use {
            it.bufferedReader().use { reader ->
                reader.readText()
            }
        }

    @Throws(Exception::class)
    fun loadUrl(urlString: String, timeout: Int): String {
        var content = ""
        val connection = URL(urlString).openConnection() as HttpURLConnection
        connection.run {
            requestMethod = "GET"
            connectTimeout = timeout
            readTimeout = timeout
            doInput = true
            connect()
            if (responseCode == HttpURLConnection.HTTP_OK) {
                content = readContent(inputStream)
            } else {
                throw Exception(responseMessage)
            }
        }
        return content
    }

}
