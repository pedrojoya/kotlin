package es.iessaladillo.pedrojoya.pr040.data.model

data class Student(
        val photo: String? = null,
        val name: String,
        val age: Int,
        val grade: String? = null,
        val address: String? = null,
        val phone: String? = null,
        val repeater: Boolean = false)
