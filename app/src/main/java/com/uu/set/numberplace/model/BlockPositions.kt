package com.uu.set.numberplace.model

import com.uu.set.numberplace.MyException

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
