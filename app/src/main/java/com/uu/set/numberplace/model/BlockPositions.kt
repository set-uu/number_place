package com.uu.set.numberplace.model

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.R

enum class BlockPositions(val row: Int, val col: Int, val viewId: Int) {
    LEFT_UP(0, 0, R.id.left_top),
    LEFT_MID(3, 0, R.id.left_middle),
    LEFT_DOWN(6, 0, R.id.left_bottom),
    CENTER_UP(0, 3, R.id.center_top),
    CENTER_MID(3, 3, R.id.center_middle),
    CENTER_DOWN(6, 3, R.id.center_bottom),
    RIGHT_UP(0, 6, R.id.right_top),
    RIGHT_MID(3, 6, R.id.right_middle),
    RIGHT_DOWN(6, 6, R.id.right_bottom), ;

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

    fun getSameRow(): MutableSet<BlockPositions> {
        val set = mutableSetOf<BlockPositions>()
        values().forEach { if (it.row == this.row && it.col != this.col) set.add(it) }
        return set
    }

    fun getSameCol(): MutableSet<BlockPositions> {
        val set = mutableSetOf<BlockPositions>()
        values().forEach { if (it.row != this.row && it.col == this.col) set.add(it) }
        return set
    }
}
