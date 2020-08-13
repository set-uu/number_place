package com.uu.set.numberplace

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uu.set.numberplace.logic.Calculate
import com.uu.set.numberplace.model.CalcResult
import com.uu.set.numberplace.view.Ad
import com.uu.set.numberplace.view.Board

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var defaultBoardModel: com.uu.set.numberplace.model.Board? = null
    private lateinit var boardView: Board

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
            val result = calcBoard()
            val bundle = Bundle()
            bundle.putSerializable(getString(R.string.calc_result), result)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        Ad.loadAd(view)
        boardView = Board(view, activity!!)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(getString(R.string.key_default_board), defaultBoardModel)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val board =
            savedInstanceState?.get(getString(R.string.key_default_board)) as com.uu.set.numberplace.model.Board?
        board?.let { defaultBoardModel = it }
        defaultBoardModel?.let {
            boardView.setupFromBoardModel(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun calcBoard(): CalcResult {
        // boardを生成する
        val board = boardView.createBoardModel()
        defaultBoardModel = board.clone()
        // 回答結果を得る
        return Calculate().calc(board)
    }
}