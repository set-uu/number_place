package com.uu.set.numberplace.logic.guess

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.model.*

// 可能性のある数字のうち一つを当てはめる
fun guess(result: CalcResult, board: Board) {
    // 回答されていないセルを取得する
    val targetCell = getTargetCell(board)
    // 可能性のある数字の最初の一つを当てはめる
    setGuessNumber(result, targetCell)
    board.updateCell(targetCell)

    // 当てはめたリストの位置、セル、数字を保持する
    val guessData = GuessData(result.boardList.size, targetCell.row, targetCell.col, targetCell.resolve)
    result.pushGuess(guessData)
    result.resolveStatus = ResolveStatus.Another
}

private fun getTargetCell(board: Board): Cell {
    for (row in 0..8) {
        for (col in 0.. 8) {
            val cell = board.rows[row][col]
            if(cell.resolve == 0) return cell
        }
    }
    throw MyException("前提が間違っています")
}

private fun setGuessNumber(
    result: CalcResult,
    cell: Cell
) {
    if (cell.candidateList.isEmpty()) {
        result.resolveStatus = ResolveStatus.NotResolved
        throw MyException("予測で全滅だった")
    }
    cell.updateResolve(cell.candidateList.first())
}
