package es.iessaladillo.pedrojoya.pr211.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.pr211.data.Repository
import es.iessaladillo.pedrojoya.pr211.data.model.Student

@Dao
interface StudentDao: Repository {

    @Query("SELECT * FROM Student ORDER BY name")
    override fun getStudents(): LiveData<List<Student>>

    @Insert
    override fun insertStudent(student: Student): Long

    @Update
    override fun updateStudent(student: Student): Int

    @Delete
    override fun deleteStudent(student: Student): Int

    @Query("SELECT * FROM Student WHERE id = :studentId")
    override fun getStudent(studentId: Long): LiveData<Student>

}
