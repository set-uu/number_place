package com.uu.set.numberplace.logic

import com.uu.set.numberplace.MyException
import com.uu.set.numberplace.model.Board
import org.junit.Test

class CalculateTest {

    @Test
    fun testCalc() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(3, 8, 0, 6, 0, 0, 0, 2, 7))
        mutableList.add(mutableListOf(1, 6, 0, 0, 0, 4, 8, 0, 0))
        mutableList.add(mutableListOf(0, 0, 4, 7, 5, 0, 0, 6, 1))
        mutableList.add(mutableListOf(4, 7, 0, 0, 0, 0, 0, 8, 0))
        mutableList.add(mutableListOf(9, 0, 3, 0, 0, 0, 1, 0, 6))
        mutableList.add(mutableListOf(0, 2, 0, 0, 0, 0, 0, 4, 5))
        mutableList.add(mutableListOf(8, 3, 0, 0, 6, 5, 7, 0, 0))
        mutableList.add(mutableListOf(0, 0, 2, 9, 0, 0, 0, 3, 8))
        mutableList.add(mutableListOf(7, 4, 0, 0, 0, 1, 0, 9, 2))
        val board = Board(mutableList)
        Calculate().calc(board)
    }

    @Test
    fun testCalc2() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 8, 0, 2, 0, 5, 0, 9, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 6, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 3, 1, 0, 0, 0, 5, 2, 0))
        mutableList.add(mutableListOf(8, 0, 0, 6, 0, 3, 0, 0, 2))
        mutableList.add(mutableListOf(0, 0, 5, 0, 0, 0, 4, 0, 0))
        mutableList.add(mutableListOf(7, 0, 0, 9, 0, 2, 0, 0, 8))
        mutableList.add(mutableListOf(0, 5, 2, 0, 0, 0, 3, 7, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 9, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 7, 0, 5, 0, 4, 0, 8, 0))
        val board = Board(mutableList)
        Calculate().calc(board)
    }


    @Test
    fun testCalc3() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 3, 0, 6, 0, 0, 2, 0, 0))
        mutableList.add(mutableListOf(0, 0, 7, 0, 0, 9, 0, 6, 0))
        mutableList.add(mutableListOf(0, 0, 0, 8, 0, 0, 0, 0, 1))
        mutableList.add(mutableListOf(6, 0, 0, 5, 7, 0, 8, 0, 3))
        mutableList.add(mutableListOf(0, 7, 0, 0, 0, 0, 0, 2, 0))
        mutableList.add(mutableListOf(9, 0, 5, 0, 8, 6, 0, 0, 7))
        mutableList.add(mutableListOf(2, 0, 0, 0, 0, 5, 0, 0, 0))
        mutableList.add(mutableListOf(0, 1, 0, 2, 0, 0, 6, 0, 0))
        mutableList.add(mutableListOf(0, 0, 6, 0, 0, 3, 0, 4, 0))
        val board = Board(mutableList)
        Calculate().calc(board)
    }

    @Test(expected = MyException::class)
    fun testCalcError() {
        // 前提が間違っていて解けない問題
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(3, 8, 1, 6, 0, 0, 0, 2, 7))
        mutableList.add(mutableListOf(1, 6, 0, 0, 0, 4, 8, 0, 0))
        mutableList.add(mutableListOf(0, 0, 4, 7, 5, 0, 0, 6, 1))
        mutableList.add(mutableListOf(4, 7, 0, 0, 0, 0, 0, 8, 0))
        mutableList.add(mutableListOf(9, 0, 3, 0, 0, 0, 1, 0, 6))
        mutableList.add(mutableListOf(0, 2, 0, 0, 0, 0, 0, 4, 5))
        mutableList.add(mutableListOf(8, 3, 0, 0, 6, 5, 7, 0, 0))
        mutableList.add(mutableListOf(0, 0, 2, 9, 0, 0, 0, 3, 8))
        mutableList.add(mutableListOf(7, 4, 0, 0, 0, 1, 0, 9, 2))
        val board = Board(mutableList)
        Calculate().calc(board)
    }
}