package es.iessaladillo.pedrojoya.pr211.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

// M for Model
interface BaseDao<M> {

    @Insert
    fun insert(model: M): Long

    @Update
    fun update(vararg model: M): Int

    @Delete
    fun delete(vararg model: M): Int

}
