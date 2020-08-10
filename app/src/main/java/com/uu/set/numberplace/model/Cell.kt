package com.uu.set.numberplace.model

import java.io.Serializable

/**
 * マス一つ分の情報を格納するモデル
 */
class Cell(val row: Int, val col: Int, var resolve: Int) :Serializable, Cloneable {
    constructor(row: Int, col: Int, resolve: Int, candidate: MutableList<Int>) :this(row, col, resolve) {
        candidateList.clear()
        candidateList.addAll(candidate.toList())
    }
    //    マスの位置
    //    決まった数字
    //    入る可能性のある数字
    val candidateList: MutableList<Int> = mutableListOf()
    var isChanged = false

    init {
        if (this.resolve == 0) {
            for (i in 1..9)
                candidateList.add(i)
        }

    }

    fun resolveString() :String {
        if (resolve <= 0 || resolve > 9) {
            return ""
        }
        return resolve.toString()
    }

    fun updateResolve(resolve: Int) {
        this.resolve = resolve
        this.candidateList.clear()
        this.isChanged = true
    }

    fun removeCandidate(number: Int) {
        val localIsChanged = this.candidateList.remove(number)
        this.isChanged = this.isChanged || localIsChanged
    }

    fun updateCandidateList(newList: MutableList<Int>) {
        if(newList.size != this.candidateList.size) this.isChanged = true
        this.candidateList.clear()
        this.candidateList.addAll(newList)
    }

    fun samePosition(cell: Cell): Boolean {
        if (this.row != cell.row) return false
        if (this.col != cell.col) return false
        return true
    }

    public override fun clone(): Cell {
        return Cell(this.row, this.col, this.resolve, this.candidateList)
    }
}