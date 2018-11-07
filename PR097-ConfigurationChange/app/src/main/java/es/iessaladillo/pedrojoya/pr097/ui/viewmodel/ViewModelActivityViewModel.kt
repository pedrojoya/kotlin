package es.iessaladillo.pedrojoya.pr097.ui.viewmodel

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr097.data.model.ScoreBoard

class ViewModelActivityViewModel : ViewModel() {

    val scoreBoard: ScoreBoard = ScoreBoard()

    fun increment() {
        scoreBoard.incrementScore();
    }

}
