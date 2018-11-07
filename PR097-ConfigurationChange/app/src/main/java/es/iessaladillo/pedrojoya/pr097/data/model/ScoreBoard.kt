package es.iessaladillo.pedrojoya.pr097.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ScoreBoard(private var _score: Int = 0): Parcelable {

    val score: Int
        get() = _score

    fun incrementScore() {
        _score++
    }

}
