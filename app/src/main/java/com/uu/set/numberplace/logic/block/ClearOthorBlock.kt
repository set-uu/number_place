package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.Cell

// 一つのブロックに入りうる数字から他のブロックの入りうる数字を消せるか調べる
fun clearOtherBlock(board: Board, intoCellList: MutableList<Cell>, block: BlockPositions, num: Int) {
    var row = 0
    var col = 0
    intoCellList.forEachIndexed { index, cell ->
        run {
            if (index == 0) {
                row = cell.row
                col = cell.col
            } else {
                if (row != cell.row) row = -1
                if (col != cell.col) col = -1
            }
        }
    }
    // 2または3箇所で直線的に並んでいる場合
    if (row != -1) {
        // その直線の他のブロックにはその数字は入らないことにする
        clearOtherBlockRow(
            board,
            row,
            block,
            num
        )
    }
    if (col != -1) {
        // その直線の他のブロックにはその数字は入らないことにする
        clearOtherBlockCol(
            board,
            col,
            block,
            num
        )
    }
}


private fun clearOtherBlockRow(
    board: Board,
    row: Int,
    block: BlockPositions,
    num: Int
) {
    // cell と同じrowかつ別ブロックから対象の数字を取り除く
    for (col in 0..8) {
        if (col < block.col || col >= block.col + 3) {
            board.rows[row][col].removeCandidate(num)
        }
    }
}

private fun clearOtherBlockCol(
    board: Board,
    col: Int,
    block: BlockPositions,
    num: Int
) {
    // cell と同じcolかつ別ブロックから対象の数字を取り除く
    for (row in 0..8) {
        if (row < block.row || row >= block.row + 3) {
            board.rows[row][col].removeCandidate(num)
        }
    }
}
