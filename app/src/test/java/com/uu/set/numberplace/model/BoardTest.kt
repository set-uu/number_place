package com.uu.set.numberplace.model

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BoardTest {
    @Test
    fun initTest() {
        val mutableList = mutableListOf<MutableList<Int>>()
        for (i in 1..9) {
            val col = mutableListOf(1,2,3,4,5,6,7,8,9)
            mutableList.add(col)
        }
        val board = Board(mutableList)
        println(board)
    }

    @Test
    fun isChangedInitTest() {
        val mutableList = mutableListOf<MutableList<Int>>()
        for (i in 1..9) {
            val col = mutableListOf(0,0,0,0,5,6,7,8,9)
            mutableList.add(col)
        }
        val board = Board(mutableList)
        // 初期状態はfalse
        assertFalse(board.isChanged)
    }

    @Test
    fun isChangedResolvedTest() {
        val mutableList = mutableListOf<MutableList<Int>>()
        for (i in 1..9) {
            val col = mutableListOf(0,0,0,0,5,6,7,8,9)
            mutableList.add(col)
        }
        val board = Board(mutableList)
        // 初期状態はfalse
        assertFalse(board.isChanged)

        // どこかしらに解答が入ったらtrueになる
        board.rows[0][0].updateResolve(2)
        assertTrue(board.isChanged)
    }

    @Test
    fun isChangedResetTest() {
        val mutableList = mutableListOf<MutableList<Int>>()
        for (i in 1..9) {
            val col = mutableListOf(0,0,0,0,5,6,7,8,9)
            mutableList.add(col)
        }
        val board = Board(mutableList)
        // 初期状態はfalse
        assertFalse(board.isChanged)

        // どこかしらに解答が入ったらtrueになる
        board.rows[0][0].updateResolve(2)
        assertTrue(board.isChanged)

        // リセットされたらfalseになる
        board.resetIsChanged()
        assertFalse(board.isChanged)
    }

    @Test
    fun isChangedCandidateRemovedTest() {
        val mutableList = mutableListOf<MutableList<Int>>()
        for (i in 1..9) {
            val col = mutableListOf(0,0,0,0,5,6,7,8,9)
            mutableList.add(col)
        }
        val board = Board(mutableList)
        // 初期状態はfalse
        assertFalse(board.isChanged)

        // 入りうる値が減ったらtrueになる
        board.rows[1][0].removeCandidate(1)
        assertTrue(board.isChanged)
    }

    @Test
    fun isChangedCandidateNotRemovedTest() {
        val mutableList = mutableListOf<MutableList<Int>>()
        for (i in 1..9) {
            val col = mutableListOf(0,0,0,0,5,6,7,8,9)
            mutableList.add(col)
        }
        val board = Board(mutableList)
        // 初期状態はfalse
        assertFalse(board.isChanged)

        // 入りうる値が減らなかったらfalseのまま
        board.rows[0][8].removeCandidate(1)
        assertFalse(board.isChanged)
    }

}