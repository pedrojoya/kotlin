package es.iessaladillo.pedrojoya.pr080.data.search

import es.iessaladillo.pedrojoya.pr080.base.AsyncLiveTask
import es.iessaladillo.pedrojoya.pr080.base.Event
import es.iessaladillo.pedrojoya.pr080.base.Resource
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

private const val SEARCH_URL = "https://www.google.es/search?hl=es&q=\""

class SearchRequest(private val text: String) : AsyncLiveTask<Resource<Event<String>>>() {

    override fun doAsync() {
        postValue(Resource.loading())
        // Simulate latency
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            return
        }
        val url = URL("$SEARCH_URL${URLEncoder.encode(text, "UTF-8")}\"")
        val connection = url.openConnection() as HttpURLConnection
        try {
            connection.run {
                requestMethod = "GET"
                setRequestProperty("User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)")
                connect()
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    postValue(Resource.success(Event(extractResultFromContent(
                            inputStream.use {
                                it.bufferedReader().use { reader ->
                                    reader.readText()
                                }
                            }
                    ))))
                } else {
                    postValue(Resource.error(Exception(responseMessage)))
                }
            }
        } catch (e: Exception) {
            postValue(Resource.error(e))
        }
    }

    private fun extractResultFromContent(contenido: String): String {
        var resultado = ""
        val ini = contenido.indexOf("Aproximadamente")
        if (ini != -1) {
            // Se busca el siguiente espacio en blanco después de
            // Aproximadamente.
            val fin = contenido.indexOf(" ", ini + 16)
            // El resultado corresponde a lo que sigue a
            // Aproximadamente, hasta el siguiente espacio en blanco.
            resultado = contenido.substring(ini + 16, fin)
        }
        return resultado
    }

}