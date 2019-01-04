package es.iessaladillo.pedrojoya.pr211.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.pr211.base.BaseDao
import es.iessaladillo.pedrojoya.pr211.data.local.model.Student

@Dao
interface StudentDao: BaseDao<Student> {

    @Query("SELECT * FROM Student ORDER BY name")
    fun queryStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM Student WHERE id = :studentId")
    fun queryStudent(studentId: Long): LiveData<Student>

    @Insert
    override fun insert(model: Student): Long

    @Update
    override fun update(vararg model: Student): Int

    @Delete
    override fun delete(vararg model: Student): Int

}
