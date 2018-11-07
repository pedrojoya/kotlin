package es.iessaladillo.pedrojoya.pr097.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreBoardTest {

    private var scoreBoard: ScoreBoard = ScoreBoard()

    @Test
    fun shouldScoreBeZeroInitially() {
        assertEquals(0, scoreBoard.score)
    }

    @Test
    fun shouldIncrementScore() {
        scoreBoard.incrementScore()

        assertEquals(1, scoreBoard.score)
    }

}