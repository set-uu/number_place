package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions
import com.uu.set.numberplace.model.Board
import org.junit.Assert.*
import org.junit.Test

class ClearFromSameLineKtTest {

    @Test
    fun testSameRow() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 0, 0, 0, 4, 3, 2, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 1, 0))
        mutableList.add(mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 1))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        val board = Board(mutableList)
        board.updateAllCell()
        clearFromSameRow(getUnresolvedDataMap(board))
        println(board)
        assertTrue(board.isChanged)
        for (col in 0..2) {
            assertTrue(board.rows[0][col].candidateList.contains(1))
            assertFalse(board.rows[1][col].candidateList.contains(1))
            assertFalse(board.rows[2][col].candidateList.contains(1))
        }
    }

    @Test
    fun testSameCol() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 1, 0))
        mutableList.add(mutableListOf(4, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(3, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(2, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 1))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 1, 0, 0, 0))
        val board = Board(mutableList)
        board.updateAllCell()
        clearFromSameCol(getUnresolvedDataMap(board))
        println(board)
        assertTrue(board.isChanged)
        for (row in 0..2) {
            assertTrue(board.rows[row][0].candidateList.contains(1))
            assertFalse(board.rows[row][1].candidateList.contains(1))
            assertFalse(board.rows[row][2].candidateList.contains(1))
        }
    }

    private fun getUnresolvedDataMap(board: Board): MutableMap<BlockPositions, UnresolvedData> {
        val unresolvedDataMap: MutableMap<BlockPositions, UnresolvedData> = mutableMapOf()
        BlockPositions.values()
            .forEach { blockPositions ->
                val unresolvedData = UnresolvedData(board, blockPositions)
                unresolvedDataMap[blockPositions] = unresolvedData
            }
        return unresolvedDataMap

    }
}