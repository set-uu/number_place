package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board

fun blocks(board: Board): Unit {
    val unresolvedDataMap: MutableMap<BlockPositions, UnresolvedData> = mutableMapOf()
    BlockPositions.values()
        .forEach { blockPositions ->
            val unresolvedData = UnresolvedData(board, blockPositions)
            oneBlock(
                board,
                blockPositions,
                unresolvedData
            )
            unresolvedDataMap[blockPositions] = unresolvedData
        }
    clearFromSameLine(unresolvedDataMap)
}

/**
 * 3*3のブロックに入る値を確認する
 */
private fun oneBlock(
    board: Board,
    block: BlockPositions,
    unresolvedData: UnresolvedData
) {
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
