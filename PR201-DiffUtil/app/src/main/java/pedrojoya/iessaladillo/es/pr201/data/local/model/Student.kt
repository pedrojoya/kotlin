package pedrojoya.iessaladillo.es.pr201.data.local.model

data class Student(val id: Int, var name: String, var address: String, var photoUrl: String) {

    fun reverseName() = name.reversed()

}
