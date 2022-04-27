package com.example.chessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentContainerView

class MainActivity: AppCompatActivity() {

    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.layout_fragment_cimetiere)
        drawingView = findViewById<DrawingView>(R.id.vMain)

        var tombstone = findViewById<ImageView>(R.id.tombstone)
        //var settings = findViewById<ImageView>(R.id.settings_icon)
        var container = findViewById<FragmentContainerView>(R.id.fragment_container)
        container.setVisibility(View.GONE)
        var hide_fragcim = true
        var hide_fragset = true


        tombstone.setOnClickListener {
            //partie pour le fragment
            val imageFragment = ImageFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            transaction.replace(R.id.fragment_container,imageFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            //partie pour afficher le cimetière
            if (hide_fragcim){
                container.setVisibility(View.VISIBLE)
                hide_fragcim = false
            }
            else{
                container.setVisibility(View.GONE)
                hide_fragcim = true
            }
            // Partie pour update le cimetière
            //var piece_cimetiere: MutableList<Piece> = drawingView.getCimetiere()

            /*for (piece in piece_cimetiere){
                when (piece.id){
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
    }

    override fun onPause() {
        super.onPause()
        drawingView.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
    }
}
