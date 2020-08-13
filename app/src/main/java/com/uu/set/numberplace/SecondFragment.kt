package com.uu.set.numberplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.uu.set.numberplace.model.CalcResult
import com.uu.set.numberplace.view.Ad
import com.uu.set.numberplace.view.Board

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var boardView: Board

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.return_input).setOnClickListener {
            findNavController().popBackStack()
        }
        Ad.loadAd(view)
        setup(view)
    }

    fun setup(view: View): Unit {
        val result: CalcResult = arguments?.get(getString(R.string.calc_result)) as CalcResult
        setResolveStatus(view, result.resolveStatus.viewString)

        boardView = Board(view, activity!!)
        boardView.setupFromBoardModel(result.boardList.last())
    }

    private fun setResolveStatus(
        view: View,
        viewString: String
    ) {
        val resolveStatusView = view.findViewById<TextView>(R.id.resolve_status)
        resolveStatusView.text = viewString
    }
}