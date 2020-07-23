package com.uu.set.numberplace

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.uu.set.numberplace.logic.Calculate
import com.uu.set.numberplace.model.Board
import java.lang.StringBuilder

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.start_calc).setOnClickListener {
            val board = calcBoard(view)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun calcBoard(view: View): Board {
        // 各セルの値をリストで取得する
        val board = Board(getViewCells(view))
        // boardを生成する
        // 回答結果を得る
        try {
            Calculate().calc(board)
        } catch (e: MyException) {
            e.message?.let { Log.d(activity?.packageName, it) }
        }
        return board
    }

    private fun getViewCells(view: View): MutableList<MutableList<Int>> {
        val rows = mutableListOf<MutableList<Int>>()
        for (row in 0..8) {
            val cols = mutableListOf<Int>()
            for (col in 0.. 8) {
                val viewStr = StringBuilder().append("cell").append(row).append(col).toString()
                val viewId = resources.getIdentifier(viewStr, "id", activity?.packageName)
                val text = view.findViewById<TextView>(viewId).text.toString()
                val num = if(text.isEmpty()) 0 else Integer.parseInt(text)
                cols.add(num)
            }
            rows.add(cols)
        }
        return rows
    }
}