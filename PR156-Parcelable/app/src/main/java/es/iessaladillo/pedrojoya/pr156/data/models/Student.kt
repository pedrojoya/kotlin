package es.iessaladillo.pedrojoya.pr156.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(var name:String, var age: Int): Parcelable
