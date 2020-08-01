package com.uu.set.numberplace.logic

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.logic.block.blocks
import com.uu.set.numberplace.model.Board

class Calculate {

    fun calc(board: Board): Board {
        // 初期化
        for (x in 0..8) {
            for (y in 0..8) board.updateCell(board.rows[x][y])
        }
        val status: ResolveStatus
        while (true) {
            // この周で変更があったか
            board.isChanged = false

            // ラインに一箇所だけ入る数字があるか
            lines(board)

            // 3*3のマスに入るものはあるか
            blocks(board)

            // 1マス単位で入るものはあるか
            oneCell(board)

            if (board.isAllResolved()) {
                status = ResolveStatus.Resolved
                break
            }
            if (!board.isChanged) {
                status = ResolveStatus.NotChange
                break
            }
        }
        if (status == ResolveStatus.NotChange) throw MyException("情報が足りないか、前提が間違っています")
        return board
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

    enum class ResolveStatus() {
        NotChange,
        Resolved
    }

}