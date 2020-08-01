package com.uu.set.numberplace.logic

import com.uu.set.numberplace.model.Board

fun oneLine(board: Board) {
    for (num in 1..9) {
        oneRow(board, num)
        oneCol(board, num)
    }
}

/**
 * 1つの数字について、行単位で確認する
 */
private fun oneRow(board: Board, num: Int) {
    // 指定の数字が行にあるか？
    for (row in 0..8) {
        val targetColList = mutableListOf<Int>()
        for (col in 0..8) {
            val cell = board.rows[row][col]
            if(cell.resolve == num) {
                // 数字が解決されているならそれ以上見ない
                targetColList.clear()
                break
            }
            if (cell.candidateList.contains(num)) {
                // 入りうる数字に指定の数字があるか
                targetColList.add(col)
            }
        }
        if (targetColList.size == 1) {
            val cell = board.rows[row][targetColList[0]]
            cell.updateResolve(num)
            board.updateCell(cell)
        }
    }
}

/**
 * 1つの数字について、列単位で確認する
 */
private fun oneCol(board: Board, num: Int) {
    // 指定の数字が列にあるか？
    for (col in 0..8) {
        val targetRowList = mutableListOf<Int>()
        for (row in 0..8) {
            val cell = board.rows[row][col]
            if(cell.resolve == num) {
                // 数字が解決されているならそれ以上見ない
                targetRowList.clear()
                break
            }
            if (cell.candidateList.contains(num)) {
                // 入りうる数字に指定の数字があるか
                targetRowList.add(row)
            }
        }
        if (targetRowList.size == 1) {
            val cell = board.rows[targetRowList[0]][col]
            cell.updateResolve(num)
            board.updateCell(cell)
        }
    }
}
