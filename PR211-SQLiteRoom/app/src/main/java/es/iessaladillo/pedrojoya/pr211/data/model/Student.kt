package es.iessaladillo.pedrojoya.pr211.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class Student (
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "phone") var phone: String,
        @ColumnInfo(name = "grade") var grade: String,
        @ColumnInfo(name="address") var address: String? = null)
