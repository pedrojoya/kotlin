package es.iessaladillo.pedrojoya.pr014.data

import android.os.Parcel
import android.os.Parcelable

data class Student(val name: String, val age: Int, val level: String,
                   val grade: String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Student> = object : Parcelable.Creator<Student> {
            override fun createFromParcel(source: Parcel): Student = Student(source)
            override fun newArray(size: Int): Array<Student?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
        dest.writeString(level)
        dest.writeString(grade)
    }

}