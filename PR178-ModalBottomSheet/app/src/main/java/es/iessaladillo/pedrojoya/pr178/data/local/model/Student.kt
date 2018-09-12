package es.iessaladillo.pedrojoya.pr178.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(val name: String, val address: String, val photoUrl: String) : Parcelable