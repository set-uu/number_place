package com.uu.set.numberplace.model

import java.io.Serializable

class CalcResult() : Serializable {
    constructor(board: Board) : this() {
        boardList.add(board.clone())
    }

    var resolveStatus: ResolveStatus = ResolveStatus.NotResolved
    val boardList = mutableListOf<Board>()
}