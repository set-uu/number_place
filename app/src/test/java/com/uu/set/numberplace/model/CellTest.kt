package com.uu.set.numberplace.model

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CellTest {
    @Test
    fun initTest() {
        val cell = Cell(1, 2, 3)
        assertEquals(1, cell.row)
        assertEquals(2, cell.col)
        assertEquals(3, cell.resolve)
        assertTrue(cell.candidateList.isEmpty())
    }

    @Test
    fun initTest2() {
        val cell = Cell(1, 2, 0)
        assertEquals(1, cell.row)
        assertEquals(2, cell.col)
        assertEquals(0, cell.resolve)
        assertFalse(cell.candidateList.isEmpty())
    }
}