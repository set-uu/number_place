package com.uu.set.numberplace.logic

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.Cell

val DEFAULT_LIST = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

class Calculate {

    fun calc(board: Board): Board {
        // 初期化
        for (x in 0..8) {
            for (y in 0..8) updatedBoard(board, board.rows[x][y])
        }
        val status: ResolveStatus
        while (true) {
            var isChanged = false
            // 3*3のマスに入るものはあるか
            BlockPositions.values()
                .forEach { blockPositions -> isChanged = isChanged || block(board, blockPositions) }
            // 1マス単位で入るものはあるか
            for (x in 0..8) {
                for (y in 0..8) isChanged = isChanged || oneCell(board, board.rows[x][y])
            }

            if (board.allResolve()) {
                status = ResolveStatus.Resolved
                break
            }
            if (!isChanged) {
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
    private fun oneCell(board: Board, cell: Cell): Boolean {
        if (cell.resolve != 0) return false
        if (cell.candidateList.size == 1) {
            cell.resolve = cell.candidateList.first()
            updatedBoard(board, cell)
            return true
        }
        return false
    }

    /**
     * 3*3のブロックに入る値を確認する
     */
    private fun block(board: Board, block: BlockPositions): Boolean {
        var isChange = false
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
                        cell.resolve = num
                        updatedBoard(board, cell)
                        isChange = true
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
                            isChange = isChange || clearOtherBlockRow(
                                board,
                                intoCellList.first(),
                                block,
                                num
                            )
                        }
                        if (col != -1) {
                            // その直線の他のブロックにはその数字は入らないことにする
                            isChange = isChange || clearOtherBlockCol(
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
        return isChange
    }

    private fun clearOtherBlockRow(
        board: Board,
        cell: Cell,
        block: BlockPositions,
        num: Int
    ): Boolean {
        // cell と同じrowかつ別ブロックから対象の数字を取り除く
        var isChange = false
        val row = cell.row
        for (col in 0..8) {
            if (col < block.col || col >= block.col + 3) {
                isChange = isChange || board.rows[row][col].candidateList.remove(num)
            }
        }

        return isChange
    }

    private fun clearOtherBlockCol(
        board: Board,
        cell: Cell,
        block: BlockPositions,
        num: Int
    ): Boolean {
        // cell と同じcolかつ別ブロックから対象の数字を取り除く
        var isChange = false
        val col = cell.col
        for (row in 0..8) {
            if (row < block.row || row >= block.row + 3) {
                isChange = isChange || board.rows[row][col].candidateList.remove(num)
            }
        }
        return isChange
    }

    /**
     * マスに入る値から行、列、3*3の入る数字を変更する
     */
    private fun updatedBoard(board: Board, cell: Cell) {
        val block = BlockPositions.get(cell.row, cell.col)
        for (x in 0..2) {
            for (y in 0..2) {
                board.rows[block.row + x][block.col + y].candidateList.remove(cell.resolve)
            }
        }

        // 同じ行を参照する
        for (col in 0..8) board.rows[cell.row][col].candidateList.remove(cell.resolve)

        // 同じ列を参照する
        for (row in 0..8) board.rows[row][cell.col].candidateList.remove(cell.resolve)
    }

    enum class ResolveStatus() {
        NotChange,
        Resolved
    }

    enum class BlockPositions(val row: Int, val col: Int) {
        LEFT_UP(0, 0),
        LEFT_MID(3, 0),
        LEFT_DOWN(6, 0),
        CENTER_UP(0, 3),
        CENTER_MID(3, 3),
        CENTER_DOWN(6, 3),
        RIGHT_UP(0, 6),
        RIGHT_MID(3, 6),
        RIGHT_DOWN(6, 6), ;

        companion object {
            fun get(row: Int, col: Int): BlockPositions {
                when (col) {
                    0, 1, 2 -> {
                        when (row) {
                            0, 1, 2 -> return LEFT_UP
                            3, 4, 5 -> return LEFT_MID
                            6, 7, 8 -> return LEFT_DOWN
                        }
                    }
                    3, 4, 5 -> {
                        when (row) {
                            0, 1, 2 -> return CENTER_UP
                            3, 4, 5 -> return CENTER_MID
                            6, 7, 8 -> return CENTER_DOWN
                        }
                    }
                    6, 7, 8 -> {
                        when (row) {
                            0, 1, 2 -> return RIGHT_UP
                            3, 4, 5 -> return RIGHT_MID
                            6, 7, 8 -> return RIGHT_DOWN
                        }
                    }
                }
                throw MyException("セル位置が不正")
            }
        }
    }
}