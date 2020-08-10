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