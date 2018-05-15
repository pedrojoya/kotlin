package pedrojoya.iessaladillo.es.pr201.data

import com.mooveit.library.Fakeit

object Database: Repository {
    private val students: ArrayList<Student> = ArrayList()

    private var autonumeric: Int = 1
    init {
        // Create initial students.
        for (i in 0..4) {
            students.add(newFakeStudent())
        }
    }

    override fun queryStudents(order: Int): List<Student> =
        if (order == 1) students.sortedBy { it.name }
        else students.sortedByDescending { it.name }

    override fun deleteStudent(student: Student) {
        students.remove(student)
    }

    override fun addStudent(student: Student) {
        students.add(student)
    }

    @Synchronized
    fun newFakeStudent(): Student {
        val num = autonumeric++
        return Student(num, Fakeit.name().name(), Fakeit.address().streetAddress(),
                "http://lorempixel.com/100/100/abstract/" + (num % 10 + 1) + "/")
    }

    override fun updateStudent(student: Student, newStudent: Student) {
        val index = students.indexOf(student)
        if (index >= 0) {
            students[index] = newStudent
        }
    }

}
