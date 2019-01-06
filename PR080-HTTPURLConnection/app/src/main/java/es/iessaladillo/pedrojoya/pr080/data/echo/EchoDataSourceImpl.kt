package es.iessaladillo.pedrojoya.pr080.data.echo

class EchoDataSourceImpl : EchoDataSource {

    override fun requestEcho(text: String): EchoRequest {
        return EchoRequest(text)
    }

}
