package com.uu.set.numberplace.logic

import com.uu.set.numberplace.logic.block.blocks
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.CalcResult
import com.uu.set.numberplace.model.ResolveStatus

class Calculate {

    fun calc(board: Board): CalcResult {
        val result = CalcResult(board)
        while (true) {
            // この周で変更があったか
            board.resetIsChanged()

            // ラインに一箇所だけ入る数字があるか
            lines(board)

            // 3*3のマスに入るものはあるか
            blocks(board)

            // 1マス単位で入るものはあるか
            oneCell(board)

            result.boardList.add(board.clone())
            if (board.isAllResolved()) {
                result.resolveStatus = ResolveStatus.Resolved
                break
            }
            if (!board.isChanged) {
                break
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