package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.Cell

fun blocks(board: Board): Unit {
    BlockPositions.values()
        .forEach { blockPositions ->
            oneBlock(
                board,
                blockPositions
            )
        }
}

/**
 * 3*3のブロックに入る値を確認する
 */
private fun oneBlock(board: Board, block: BlockPositions) {
    val unresolvedData = UnresolvedData(board, block)
    unresolvedData.numberList.forEach { num ->
        run {
            val intoCellList = mutableListOf<Cell>()
            unresolvedData.cellList.forEach { cell ->
                if (cell.candidateList.contains(num)) intoCellList.add(cell)
            }
            when (intoCellList.size) {
                1 -> {
                    // 可能性のある場所が1箇所だけなら当てはめる
                    val cell = intoCellList.first()
                    cell.updateResolve(num)
                    board.updateCell(cell)
                }
                2, 3 -> {
                    clearOtherBlock(board, intoCellList, block, num)
                }
            }
        }
    }
}
