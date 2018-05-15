package pedrojoya.iessaladillo.es.pr201.data

data class Student(val id: Int, var name: String, var address: String, var photoUrl: String):
        Comparable<Student> {

    fun reverseName() = name.reversed()

    override fun compareTo(other: Student): Int = name.compareTo(other.name)

}
