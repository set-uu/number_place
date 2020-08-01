package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.Cell


class UnresolvedData(board: Board, block: BlockPositions) {
    // まだ解決していない数字
    val numberList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // まだ解決していないセル
    val cellList = mutableListOf<Cell>()

    init {
        // 入っていない数字が入る可能性のある場所を取得
        for (x in 0..2) {
            for (y in 0..2) {
                val cell = board.rows[block.row + x][block.col + y]
                if (cell.resolve != 0)
                    numberList.remove(cell.resolve)
                else
                    cellList.add(cell)
            }
        }
    }
}