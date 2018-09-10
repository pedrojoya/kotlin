package pedrojoya.iessaladillo.es.pr228.data.local.model

class Student(val name: String, val address: String, val photoUrl: String) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val student = o as Student?

        return name == student!!.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}
