package com.uu.set.numberplace.logic

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.logic.block.blocks
import com.uu.set.numberplace.logic.guess.guess
import com.uu.set.numberplace.logic.guess.rollBack
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.CalcResult
import com.uu.set.numberplace.model.ResolveStatus

class Calculate {

    fun calc(board: Board): CalcResult {
        var currentBoard = board
        val result = CalcResult(currentBoard)
        while (true) {
            // この周で変更があったか
            currentBoard.resetIsChanged()

            // ラインに一箇所だけ入る数字があるか
            lines(currentBoard)

            // 3*3のマスに入るものはあるか
            blocks(currentBoard)

            // 1マス単位で入るものはあるか
            oneCell(currentBoard)

            result.boardList.add(currentBoard.clone())
            if (currentBoard.isAllResolved()) {
                if (result.resolveStatus != ResolveStatus.Another)
                    result.resolveStatus = ResolveStatus.Resolved
                break
            }
            if (!currentBoard.isChanged) {
                // 入りうる値がないセルがある → ロールバックする
                try {
                    if (currentBoard.hasNoCandidateCell) {
                        currentBoard = rollBack(result)
                    }
                    // 別解を探す
                    guess(result, currentBoard)
                } catch (e: MyException) {
                    // ロールバックする先がない → 詰み
                    return result
                }

            }
        }
        return result
    }

    /**
     * 一つのマスに入りうる数字が一つだけのときはその値を入れる
     */
    private fun oneCell(board: Board) {
        for (row in 0..8) {
            for (col in 0..8) {
                val cell = board.rows[row][col]
                if (cell.resolve != 0) continue
                if (cell.candidateList.size == 1) {
                    cell.updateResolve(cell.candidateList.first())
                    board.updateCell(cell)
                }
            }
        }
    }

}