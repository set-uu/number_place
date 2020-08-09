package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.Cell


class UnresolvedData(board: Board, block: BlockPositions) {
    // まだ解決していない数字とcellの組
    val numberMap: MutableMap<Int, MutableList<Cell>> = mutableMapOf()

    init {
        // 入っていない数字が入る可能性のある場所を取得
        for (x in 0..2) {
            for (y in 0..2) {
                val cell = board.rows[block.row + x][block.col + y]
                if (cell.resolve != 0) continue
                cell.candidateList.forEach { addNumberMap(it, cell) }
            }
        }
    }

    private fun addNumberMap(number: Int, cell: Cell) {
        if (numberMap.containsKey(number)) {
            numberMap[number]?.add(cell)
        } else {
            val cellList = mutableListOf(cell)
            numberMap[number] = cellList
        }

    }

    fun rowPositions(number: Int): MutableSet<Int>? {
        return this.numberMap[number]?.map { cell -> cell.row }?.toSortedSet()
    }

    fun colPositions(number: Int): MutableSet<Int>? {
        return this.numberMap[number]?.map { cell -> cell.col }?.toSortedSet()
    }

}