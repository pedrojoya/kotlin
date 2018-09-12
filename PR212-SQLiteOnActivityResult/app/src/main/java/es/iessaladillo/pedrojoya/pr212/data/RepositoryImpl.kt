package es.iessaladillo.pedrojoya.pr212.data

import es.iessaladillo.pedrojoya.pr212.data.local.StudentDao
import es.iessaladillo.pedrojoya.pr212.data.local.model.Student

class RepositoryImpl (private val studentDao: StudentDao) : Repository {

    override fun getStudents(): List<Student> = studentDao.getStudents()

    override fun getStudent(studentId: Long): Student? = studentDao.getStudent(studentId)

    override fun addStudent(student: Student): Long = studentDao.createStudent(student)

    override fun updateStudent(student: Student): Boolean = studentDao.updateStudent(student)

    override fun deleteStudent(studentId: Long): Boolean = studentDao.deleteStudent(studentId)

    override fun onDestroy()  {
        studentDao.closeDatabase()
    }

}
