package es.iessaladillo.pedrojoya.pr080.data.echo

import es.iessaladillo.pedrojoya.pr080.base.Call
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

private const val KEY_NAME = "nombre"
private const val KEY_DATE = "fecha"
private const val ECHO_URL = "http://www.informaticasaladillo.es/echo.php"

class EchoRequest(private val text: String) : Call<Resource<Event<String>>>() {

    private val simpleDateFormat = SimpleDateFormat(
            "dd/MM/yyyy " + "HH:mm:ss", Locale.getDefault())

    override fun doAsync() {
        postValue(Resource.loading())
        // Simulate latency
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            return
        }
        val connection = URL(ECHO_URL).openConnection() as HttpURLConnection
        connection.run {
            try {
                requestMethod = "POST"
                doInput = true
                doOutput = true
                PrintWriter(connection.outputStream).use {
                    it.write(KEY_NAME + "=" +
                            URLEncoder.encode(text, "UTF-8"))
                    it.write("&$KEY_DATE=" + URLEncoder.encode(simpleDateFormat.format(Date()), "UTF-8"))
                    it.flush()
                }
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    postValue(Resource.success(Event(
                            inputStream.use {
                                it.bufferedReader().use { reader ->
                                    reader.readText().trim()
                                }
                            }
                    )))
                } else {
                    postValue(Resource.error(Exception(responseMessage)))
                }
            } catch (e: Exception) {
                postValue(Resource.error(e))
            }
        }
    }

}
