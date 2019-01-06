package es.iessaladillo.pedrojoya.pr080.data.echo

interface EchoDataSource {

    fun requestEcho(text: String): EchoRequest

}
