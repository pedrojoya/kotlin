package es.iessaladillo.pedrojoya.pr178.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(var id: Long, val name: String, val address: String, val photoUrl: String) : Parcelable