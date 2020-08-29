package com.uu.set.numberplace.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.util.*

/**
 * 盤面全体の情報モデル
 */
fun boardFromCells(data: MutableList<MutableList<Cell>>): Board {
    val board = Board()
    board.rows = data
    return board
}

class Board() : Serializable, Cloneable {
    constructor(data: MutableList<MutableList<Int>>) : this() {
        this.rows = initRows(data = data)
        updateAllCell()
        resetIsChanged()
    }

    val isChanged: Boolean
        get() {
            for (row in 0..8) {
                for (col in 0..8) {
                    if (rows[row][col].isChanged) return true
                }
            }
            return false
        }

    /**
     * 盤面に矛盾がないかチェックする
     */
    val isInconsistent: Boolean
        get() {
            return hasRowInconsistent() || hasColInconsistent() || hasBlockInconsistent()
        }

    val hasNoCandidateCell: Boolean
        get() {
            for (row in 0..8) {
                for (col in 0..8) {
                    val cell = rows[row][col]
                    if (cell.resolve == 0 && cell.candidateList.isEmpty()) return true
                }
            }
            return false
        }

    lateinit var rows: MutableList<MutableList<Cell>>
    var resolveStatus: String = ""

    private fun initRows(data: MutableList<MutableList<Int>>): MutableList<MutableList<Cell>> {
        val rowList = mutableListOf<MutableList<Cell>>()
        data.forEachIndexed { row, list ->
            run {
                val cols = mutableListOf<Cell>()
                list.forEachIndexed { col, i ->
                    run {
                        cols.add(Cell(row, col, i))
                    }
                }
                rowList.add(cols)
            }
        }
        return rowList
    }

    fun isAllResolved(): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                if (rows[row][col].resolve == 0) return false
            }
        }
        return true
    }

    fun updateAllCell() {
        rows.forEach {
            it.forEach { cell ->
                this.updateCell(cell)
            }
        }
    }

    /**
     * マスに入る値から行、列、3*3の入る数字を変更する
     */
    fun updateCell(cell: Cell) {
        cell.isChanged = true
        val block = BlockPositions.get(cell.row, cell.col)
        for (x in 0..2) {
            for (y in 0..2) {
                rows[block.row + x][block.col + y].removeCandidate(cell.resolve)
            }
        }

        // 同じ行を参照する
        for (col in 0..8) rows[cell.row][col].removeCandidate(cell.resolve)

        // 同じ列を参照する
        for (row in 0..8) rows[row][cell.col].removeCandidate(cell.resolve)
    }

    fun resetIsChanged() {
        for (row in 0..8) {
            for (col in 0..8) {
                this.rows[row][col].isChanged = false
            }
        }
    }

    /**
     * 同じ行に矛盾があるかチェックする
     */
    private fun hasRowInconsistent(): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                val baseCell = this.rows[row][col]
                if (baseCell.resolve != 0) {
                    for (diffCol in col + 1..8) {
                        if (baseCell.resolve == this.rows[row][diffCol].resolve) return true;
                    }
                }
            }
        }
        return false
    }

    /**
     * 同じ列に矛盾があるかチェックする
     */
    private fun hasColInconsistent(): Boolean {
        for (col in 0..8) {
            for (row in 0..8) {
                val baseCell = this.rows[row][col]
                if (baseCell.resolve != 0) {
                    for (diffRow in row + 1..8) {
                        if (baseCell.resolve == this.rows[diffRow][col].resolve) return true;
                    }
                }
            }
        }
        return false
    }

    /**
     * 同じブロックに矛盾があるかチェックする
     */
    private fun hasBlockInconsistent(): Boolean {
        BlockPositions.values().forEach {
            for (row in 0..2) {
                for (col in 0..2) {
                    val baseCell = this.rows[it.row + row][it.col + col]
                    if (baseCell.resolve != 0) {
                        for (diffRow in 0..2) {
                            for (diffCol in 0..2) {
                                val diffCell = this.rows[it.row + diffRow][it.col + diffCol]
                                if (baseCell.row != diffCell.row &&
                                    baseCell.col != diffCell.col &&
                                    baseCell.resolve == diffCell.resolve
                                ) {
                                    return true
                                }
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun toString(): String {
        val rowJoiner = StringJoiner(System.lineSeparator())
        for (row in 0..8) {
            val colJoiner = StringJoiner("|", "|", "|")
            for (col in 0..8) {
                val resolve =
                    if (rows[row][col].resolve == 0) " " else rows[row][col].resolve.toString()
                colJoiner.add(resolve)
            }
            rowJoiner.add(colJoiner.toString())
        }

        return rowJoiner.toString()
    }

    public override fun clone(): Board {
        val newRows = mutableListOf<MutableList<Cell>>()
        rows.forEach {
            val newRow = mutableListOf<Cell>()
            it.forEach { cell ->
                newRow.add(cell.clone())
            }
            newRows.add(newRow)
        }

        return boardFromCells(newRows)
    }
}