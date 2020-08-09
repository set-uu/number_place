package com.uu.set.numberplace.logic.block

import com.uu.set.numberplace.model.BlockPositions

fun clearFromSameLine(
    unresolvedDataMap: MutableMap<BlockPositions, UnresolvedData>
) {
    // 他の2つのブロックで決定していない場合、可能性のあるマスの位置を取得する
    clearFromSameRow(unresolvedDataMap)
    clearFromSameCol(unresolvedDataMap)
}

fun clearFromSameRow(unresolvedDataMap: MutableMap<BlockPositions, UnresolvedData>) {
    unresolvedDataMap.forEach { (block, unresolvedData) ->
        // 解答対象のブロックについて同じラインのブロックを取得する
        val sameRowDataList = mutableListOf<UnresolvedData>()
        block.getSameRow().forEach {
            unresolvedDataMap[it]?.let { it1 ->
                sameRowDataList.add(
                    it1
                )
            }
        }

        // 解答対象のブロックの未回答の数字について見る
        unresolvedData.numberMap.forEach number@{ (number, candidateCells) ->
            val rowPositions = mutableSetOf<Int>()
            sameRowDataList.forEach { sameRowBlockUnresolvedData ->
                // 他の2つのブロックで同じ2つの行/列だけに数字の可能性がある
                if (!sameRowBlockUnresolvedData.numberMap.containsKey(number)) {
                    // 片方は解答が出ている場合、それ以上調べる必要がないため次の数字に行く
                    return@number
                }
                sameRowBlockUnresolvedData.rowPositions(number)?.let { positions ->
                    rowPositions.addAll(positions)
                }
            }
            if (rowPositions.size == 2) {
                // その2つの行/列にはその数字は入らない
                rowPositions.forEach { row ->
                    candidateCells.forEach { cell ->
                        if (cell.row == row) cell.removeCandidate(number)
                    }
                }
            }
        }
    }
}

fun clearFromSameCol(unresolvedDataMap: MutableMap<BlockPositions, UnresolvedData>) {
    unresolvedDataMap.forEach { (block, unresolvedData) ->
        // 解答対象のブロックについて同じラインのブロックを取得する
        val sameColDataList = mutableListOf<UnresolvedData>()
        block.getSameCol().forEach {
            unresolvedDataMap[it]?.let { it1 ->
                sameColDataList.add(
                    it1
                )
            }
        }

        // 解答対象のブロックの未回答の数字について見る
        unresolvedData.numberMap.forEach number@{ (number, candidateCells) ->
            val colPositions = mutableSetOf<Int>()
            sameColDataList.forEach { sameColBlockUnresolvedData ->
                // 他の2つのブロックで同じ2つの行/列だけに数字の可能性がある
                if (!sameColBlockUnresolvedData.numberMap.containsKey(number)) {
                    // 片方は解答が出ている場合、それ以上調べる必要がないため次の数字に行く
                    return@number
                }
                sameColBlockUnresolvedData.colPositions(number)?.let { positions ->
                    colPositions.addAll(positions)
                }
            }
            if (colPositions.size == 2) {
                // その2つの行/列にはその数字は入らない
                colPositions.forEach { col ->
                    candidateCells.forEach { cell ->
                        if (cell.col == col) cell.removeCandidate(number)
                    }
                }
            }
        }
    }
}