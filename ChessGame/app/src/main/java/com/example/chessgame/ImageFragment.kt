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
    lateinit var piece1:ImageView
    lateinit var piece2:ImageView
    lateinit var piece3:ImageView
    lateinit var piece4:ImageView
    lateinit var piece5:ImageView
    lateinit var piece6:ImageView
    lateinit var piece7:ImageView
    lateinit var piece8:ImageView
    lateinit var piece9:ImageView
    lateinit var piece10:ImageView
    lateinit var piece11:ImageView

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
        println("imagefragment crée")
        return view
    }
    fun UpdateXml(piece_mortes: MutableList<Piece>, view: View){
        println("appel de updatexml")
        piece0 =  view.findViewById(R.id.piece0)
        piece1 =  view.findViewById(R.id.piece1)
        piece2 =  view.findViewById(R.id.piece2)
        piece3 =  view.findViewById(R.id.piece3)
        piece4 =  view.findViewById(R.id.piece4)
        piece5 =  view.findViewById(R.id.piece5)
        piece6 =  view.findViewById(R.id.piece6)
        piece7 =  view.findViewById(R.id.piece7)
        piece8 =  view.findViewById(R.id.piece8)
        piece9 =  view.findViewById(R.id.piece9)
        piece10 =  view.findViewById(R.id.piece10)
        piece11 =  view.findViewById(R.id.piece11)
        var Imagelist: MutableList<ImageView> = mutableListOf(piece0,piece1,piece2,piece3,piece4,piece5,piece6,piece7,piece8,piece9,piece10,piece11)

        var count = 0
        for (piece in piece_mortes){
            Imagelist[count].setImageResource(piece.image)
            Imagelist[count].visibility = View.VISIBLE
            count+=1
            }
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