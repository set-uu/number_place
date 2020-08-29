package com.uu.set.numberplace.model

import java.io.Serializable

enum class ResolveStatus(val viewString: String) : Serializable {
    NotResolved("情報が足りないか、前提が間違っています"),
    Resolved("解析完了"),
    Another("一部推測があります。別解があるかもしれません")
}
