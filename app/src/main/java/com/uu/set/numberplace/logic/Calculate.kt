package com.uu.set.numberplace.logic

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.Cell

val DEFAULT_LIST = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

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
            oneLine(board)

            // 3*3のマスに入るものはあるか
            BlockPositions.values()
                .forEach { blockPositions -> block(board, blockPositions) }

            // 1マス単位で入るものはあるか
            for (x in 0..8) {
                for (y in 0..8) oneCell(board, board.rows[x][y])
            }

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
    private fun oneCell(board: Board, cell: Cell) {
        if (cell.resolve != 0) return
        if (cell.candidateList.size == 1) {
            cell.updateResolve(cell.candidateList.first())
            board.updateCell(cell)
        }
    }

    /**
     * 3*3のブロックに入る値を確認する
     */
    private fun block(board: Board, block: BlockPositions) {
        val resolvedList = mutableListOf<Int>()
        // 入っていない数字が入る可能性のある場所を取得
        val unResolvedCell = mutableListOf<Cell>()
        for (x in 0..2) {
            for (y in 0..2) {
                val cell = board.rows[block.row + x][block.col + y]
                if (cell.resolve != 0)
                    resolvedList.add(cell.resolve)
                else
                    unResolvedCell.add(cell)
            }
        }

        // ブロックに入っていない数字を取得
        val unResolvedList = DEFAULT_LIST.subtract(resolvedList)
        unResolvedList.forEach { num ->
            run {
                val intoCellList = mutableListOf<Cell>()
                unResolvedCell.forEach { cell ->
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
                                intoCellList.first(),
                                block,
                                num
                            )
                        }
                        if (col != -1) {
                            // その直線の他のブロックにはその数字は入らないことにする
                            clearOtherBlockCol(
                                board,
                                intoCellList.first(),
                                block,
                                num
                            )
                        }
                    }
                }
            }
        }
    }

    private fun clearOtherBlockRow(
        board: Board,
        cell: Cell,
        block: BlockPositions,
        num: Int
    ) {
        // cell と同じrowかつ別ブロックから対象の数字を取り除く
        val row = cell.row
        for (col in 0..8) {
            if (col < block.col || col >= block.col + 3) {
                val isChange = board.rows[row][col].candidateList.remove(num)
                board.isChanged = board.isChanged || isChange
            }
        }
    }

    private fun clearOtherBlockCol(
        board: Board,
        cell: Cell,
        block: BlockPositions,
        num: Int
    ) {
        // cell と同じcolかつ別ブロックから対象の数字を取り除く
        val col = cell.col
        for (row in 0..8) {
            if (row < block.row || row >= block.row + 3) {
                val isChange = board.rows[row][col].candidateList.remove(num)
                board.isChanged = board.isChanged || isChange
            }
        }
    }

    enum class ResolveStatus() {
        NotChange,
        Resolved
    }

}