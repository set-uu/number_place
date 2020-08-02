package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.Cell

fun clearSameBlock(unresolvedData: UnresolvedData) {
    // 入りうる場所が2箇所だけ かつ 入りうる場所が完全一致する2つの数字を探す
    val matchNumberSetList : MutableList<MutableSet<Int>> = mutableListOf()
    for (baseNumber in 1..8) {
        val baseCellList = unresolvedData.numberMap[baseNumber] ?: continue
        if (baseCellList.size != 2) continue

        for (diffNumber in (baseNumber + 1)..9) {
            val diffCellList = unresolvedData.numberMap[diffNumber] ?: continue

            if (isMatchCandidateCells(baseCellList, diffCellList)) {
                // そのマスはbaseNumber,diffNumberのみを可能性とする
                val newCandidateList = mutableListOf(baseNumber, diffNumber)
                baseCellList.forEach{it.updateCandidateList(newCandidateList)}
            }
        }
    }
}

private fun isMatchCandidateCells(
    baseCellList: MutableList<Cell>,
    diffCellList: MutableList<Cell>
): Boolean {
    if (baseCellList.size != diffCellList.size) return false

    baseCellList.forEach {baseCell ->
        var isSame = false
        diffCellList.forEach diffLoop@{ diffCell ->
            if (baseCell.samePosition(diffCell)) {
                isSame = true
                return@diffLoop
            }
        }
        if (!isSame) return false
    }
    return true
}