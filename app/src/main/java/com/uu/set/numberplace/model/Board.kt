package com.uu.set.numberplace.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

/**
 * 盤面全体の情報モデル
 */
class Board(private val data: List<List<Int>>) {
    val rows: MutableList<MutableList<Cell>> = mutableListOf()

    init {
        data.forEachIndexed { row, list ->
            run {
                val cols = mutableListOf<Cell>()
                list.forEachIndexed { col, i ->
                    run {
                        cols.add(Cell(row, col, i))
                    }
                }
                rows.add(cols)
            }
        }
    }

    fun allResolve(): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                if (rows[row][col].resolve == 0) return false
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun toString(): String {
        val rowJoiner = StringJoiner(System.lineSeparator())
        for (row in 0..8) {
            val colJoiner = StringJoiner("|", "|", "|")
            for (col in 0..8) {
                val resolve = if (rows[row][col].resolve == 0) " " else rows[row][col].resolve.toString()
                colJoiner.add(resolve)
            }
            rowJoiner.add(colJoiner.toString())
        }

        return rowJoiner.toString()
    }
}