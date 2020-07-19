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

}