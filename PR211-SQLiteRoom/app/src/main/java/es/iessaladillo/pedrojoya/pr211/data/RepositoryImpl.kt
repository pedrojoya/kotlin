package es.iessaladillo.pedrojoya.pr211.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.pr211.data.local.StudentDao
import es.iessaladillo.pedrojoya.pr211.data.local.model.Student

class RepositoryImpl(private val studentDao: StudentDao) : Repository {

    override fun queryStudents(): LiveData<List<Student>> = studentDao.queryStudents()

    override fun queryStudent(studentId: Long): LiveData<Student> = studentDao.queryStudent(studentId)

    override fun insertStudent(student: Student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute { studentDao.insert(student) }
    }

    override fun updateStudent(student: Student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute { studentDao.update(student) }
    }

    override fun deleteStudent(student: Student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute { studentDao.delete(student) }
    }

}
