package com.uu.set.numberplace.logic

import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.model.ResolveStatus
import org.junit.Assert
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
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.Resolved, result.resolveStatus)
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
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.Resolved, result.resolveStatus)
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
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.Resolved, result.resolveStatus)
    }

    // 高難度問題
    @Test
    fun testCalc4() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(5, 0, 0, 0, 7, 0, 6, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 8, 1, 0, 2, 7, 0))
        mutableList.add(mutableListOf(0, 2, 7, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 1, 5, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 5, 3, 0, 1, 9, 0))
        mutableList.add(mutableListOf(3, 0, 0, 0, 2, 0, 8, 0, 0))
        mutableList.add(mutableListOf(0, 9, 8, 7, 0, 0, 0, 2, 0))
        mutableList.add(mutableListOf(1, 0, 0, 0, 0, 5, 0, 6, 0))
        mutableList.add(mutableListOf(2, 0, 0, 0, 0, 3, 0, 0, 8))
        val board = Board(mutableList)
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.Another, result.resolveStatus)
    }

    // 高難度問題
    @Test
    fun testCalc5() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 0, 0, 9, 0, 2, 0, 0, 5))
        mutableList.add(mutableListOf(0, 0, 5, 0, 0, 0, 3, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 6, 8, 0, 7, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 1, 8, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 1, 0, 2, 0, 6))
        mutableList.add(mutableListOf(0, 0, 0, 8, 0, 3, 0, 0, 0))
        mutableList.add(mutableListOf(5, 0, 1, 0, 0, 0, 0, 0, 7))
        mutableList.add(mutableListOf(6, 0, 0, 0, 0, 0, 0, 9, 4))
        mutableList.add(mutableListOf(7, 0, 3, 0, 0, 4, 0, 0, 0))
        val board = Board(mutableList)
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.Another, result.resolveStatus)
    }

    // 高難度問題
    @Test
    fun testCalc6() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(5, 0, 0, 2, 0, 0, 0, 0, 9))
        mutableList.add(mutableListOf(0, 7, 0, 0, 8, 0, 0, 3, 0))
        mutableList.add(mutableListOf(0, 2, 0, 0, 6, 0, 0, 1, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 9, 0, 2, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 7, 0, 0, 0, 6))
        mutableList.add(mutableListOf(1, 9, 0, 0, 0, 0, 0, 0, 8))
        mutableList.add(mutableListOf(0, 0, 2, 0, 0, 0, 1, 6, 5))
        mutableList.add(mutableListOf(0, 4, 0, 0, 0, 7, 0, 0, 0))
        mutableList.add(mutableListOf(8, 0, 9, 5, 0, 0, 0, 0, 0))
        val board = Board(mutableList)
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.Another, result.resolveStatus)
    }

    // あぶり出し確認
    @Test
    fun testCalc_abridasi1() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 1, 2, 3))
        mutableList.add(mutableListOf(1, 2, 3, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 2, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 3, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        val board = Board(mutableList)
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(1, result.boardList.last().rows[0][3].resolve)
        Assert.assertEquals(ResolveStatus.Another, result.resolveStatus)
    }

    // あぶり出し確認
    @Test
    fun testCalc_abridasi2() {
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 2, 3))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 2, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 3, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 1, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 1, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 2, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 3, 0, 0, 0, 0))
        val board = Board(mutableList)
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(1, result.boardList.last().rows[1][5].resolve)
        Assert.assertEquals(ResolveStatus.Another, result.resolveStatus)
    }

    @Test
    fun testCalcError() {
        // 前提が間違っていて解けない問題
        val mutableList = mutableListOf<MutableList<Int>>()
        mutableList.add(mutableListOf(1, 0, 0, 0, 0, 0, 0, 0, 1))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        mutableList.add(mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        val board = Board(mutableList)
        assert(board.isInconsistent)
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.NotResolved, result.resolveStatus)
    }

    @Test
    fun testCalcError2() {
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
        val result = Calculate().calc(board)
        println(result.boardList.last())
        Assert.assertEquals(ResolveStatus.NotResolved, result.resolveStatus)
    }
}