package com.uu.set.numberplace.model

import java.io.Serializable

class CalcResult() : Serializable {
    constructor(board: Board) : this() {
        boardList.add(board.clone())
    }

    var resolveStatus: ResolveStatus = ResolveStatus.NotResolved
    val boardList = mutableListOf<Board>()
    private val guessList = mutableListOf<GuessData>()

    fun pushGuess(guessData: GuessData) {
        guessList.add(guessData)
    }

    fun popGuess(): GuessData? {
        val guessData = guessList.lastOrNull()
        if (guessData != null) guessList.remove(guessData)
        return guessData
    }
}