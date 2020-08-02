package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board

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
    unresolvedData.numberMap.forEach { (num, cellList) ->
        run {
            when (cellList.size) {
                1 -> {
                    // 可能性のある場所が1箇所だけなら当てはめる
                    val cell = cellList.first()
                    cell.updateResolve(num)
                    board.updateCell(cell)
                }
                2, 3 -> {
                    clearOtherBlock(board, cellList, block, num)
                }
            }
        }
    }
    clearSameBlock(unresolvedData)
}
