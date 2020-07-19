package com.uu.set.numberplace.model

/**
 * マス一つ分の情報を格納するモデル
 */
class Cell(val row: Int, val col: Int, var resolve: Int) {
    //    マスの位置
    //    決まった数字
    //    入る可能性のある数字
    val candidateList: MutableList<Int> = mutableListOf()

    init {
        if (this.resolve == 0) {
            for (i in 1..9)
                candidateList.add(i)
        }

    }

}