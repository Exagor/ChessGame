package com.example.chessgame

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment


class ImageFragment : Fragment() {
    lateinit var piece0:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Return the fragment view/layout
        var view = inflater.inflate(R.layout.layout_fragment_cimetiere,container,false)
        piece0 =  view.findViewById(R.id.piece0)
        //piece0.visibility = View.VISIBLE
        println("imagefragment crée")
        return view
    }
    fun UpdateXml(piece_mortes: MutableList<Piece>, view: View){
        println("appel de updatexml")
        piece0 =  view.findViewById(R.id.piece0)
        piece0.visibility = View.VISIBLE
        /*for (piece in piece_mortes){
            when (piece){
                0 -> piece0.setVisibility(View.VISIBLE)
                1 -> piece1.setVisibility(View.VISIBLE)
                2 -> piece2.setVisibility(View.VISIBLE)
                3 -> piece3.setVisibility(View.VISIBLE)
                4 -> piece4.setVisibility(View.VISIBLE)
                5 -> piece5.setVisibility(View.VISIBLE)
                6 -> piece6.setVisibility(View.VISIBLE)
                7 -> piece7.setVisibility(View.VISIBLE)
                8 -> piece8.setVisibility(View.VISIBLE)
                9 -> piece9.setVisibility(View.VISIBLE)
            }
        }*/

    }
    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}