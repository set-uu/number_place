package com.uu.set.numberplace.view

import android.content.Context
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import java.io.Serializable

class Board(private val view: View, private val context: Context) : Serializable {
    fun createBoardModel(): Board {
        return Board(getCellsNumber())
    }

    private fun getCellsNumber(): MutableList<MutableList<Int>> {
        val rows = MutableList(9){ MutableList(9) {0}}
        BlockPositions.values().forEach { blockPosition ->
            val blockView = view.findViewById<GridLayout>(blockPosition.viewId)
            for (row in 0..2) {
                for (col in 0.. 2) {
                    val viewId = getViewId(row, col)
                    val text = blockView.findViewById<TextView>(viewId).text.toString()
                    val num = if (text.isEmpty()) 0 else Integer.parseInt(text)
                    val oneRow = rows[row + blockPosition.row]
                    oneRow[col + blockPosition.col] = num
                }
            }
        }
        return rows
    }

    fun setupFromBoardModel(board: Board) {
        BlockPositions.values().forEach { blockPosition ->
            val blockView = view.findViewById<GridLayout>(blockPosition.viewId)
            for (row in 0..2) {
                for (col in 0.. 2) {
                    val cell = board.rows[row+blockPosition.row][col+blockPosition.col]
                    val viewId = getViewId(row, col)
                    blockView.findViewById<TextView>(viewId).text = cell.resolveString()
                }
            }
        }
    }

    private fun getViewId(row: Int, col: Int): Int {
        val viewStr = StringBuilder().append("cell").append(row).append(col).toString()
        return context.resources.getIdentifier(viewStr, "id", context.packageName)
    }

}
