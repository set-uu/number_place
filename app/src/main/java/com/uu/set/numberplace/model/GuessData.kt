package com.uu.set.numberplace.model

import java.io.Serializable

// 推測した情報を保持しておく
data class GuessData(val index : Int, val row: Int, val col: Int, val number: Int) : Serializable {
}