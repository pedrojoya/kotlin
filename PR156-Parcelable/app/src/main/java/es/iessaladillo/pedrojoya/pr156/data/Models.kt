package es.iessaladillo.pedrojoya.pr156.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

const val DEFAULT_AGE = 18

@Parcelize
data class Student(var name:String, var age: Int): Parcelable
