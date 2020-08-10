package com.uu.set.numberplace.logic.guess

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.CalcResult
import com.uu.set.numberplace.model.ResolveStatus

fun rollBack(result: CalcResult): Board {
    // 最後に予測した場所へ戻る
    val guessData = result.popGuess()
    if (guessData == null) {
        result.resolveStatus = ResolveStatus.NotResolved
        throw MyException("戻り先なし")
    }
    val removeBoardList = result.boardList.subList(guessData.index, result.boardList.size)
    result.boardList.removeAll(removeBoardList)
    val cell = result.boardList.last().rows[guessData.row][guessData.col]
    cell.removeCandidate(guessData.number)
    return result.boardList.last()
}