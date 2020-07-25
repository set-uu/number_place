package com.uu.set.numberplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.uu.set.numberplace.model.Board
import com.uu.set.numberplace.view.Ad

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

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
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        Ad.loadAd(view)
        setup(view)
    }

    fun setup(view: View): Unit {
        val packageName = activity?.packageName
        val board :Board = arguments?.get("board") as Board

        val resolveStatus = view.findViewById<TextView>(resources.getIdentifier("resolve_status", "id", packageName))
        resolveStatus.text = board.resolveStatus
        for (row in 0..8) {
            for (col in 0..8) {
                val cell = view.findViewById<TextView>(resources.getIdentifier("cell$row$col", "id", packageName))
                cell.text = board.rows[row][col].resolveString()
            }
        }
    }
}