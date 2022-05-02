package com.example.chessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentContainerView

class EchecActivity: AppCompatActivity() {

    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_echec)
        drawingView = findViewById<DrawingView>(R.id.vMain)

        var tombstone = findViewById<ImageView>(R.id.tombstone)
        var settings = findViewById<ImageView>(R.id.settings_icon)
        var container = findViewById<FragmentContainerView>(R.id.fragment_container)
        container.visibility = View.GONE
        val imageFragment = ImageFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container,imageFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        var hide_fragcim = true
        var hide_fragset = true
        val PlayerNameWhite = intent.getStringExtra("Nom du joueur (blancs):")
        val PlayerNameBlack = intent.getStringExtra("Nom du joueur (noirs):")
        val JoueurBlanc = Joueur(PlayerNameWhite, "white")
        val JoueurNoir = Joueur(PlayerNameBlack, "black")

        tombstone.setOnClickListener {
            //partie pour le fragment

            var piece_cimetiere: MutableList<Piece> = drawingView.getCimetiere()


            var truc:View? = imageFragment.view
            if (truc!=null){
                imageFragment.UpdateXml(piece_cimetiere, truc)
            }
            //partie pour afficher le cimetière
            if (hide_fragcim){
                container.visibility = View.VISIBLE
                hide_fragcim = false
            }
            else{
                container.visibility = View.GONE
                hide_fragcim = true
            }

            // Partie pour update le cimetière

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
        settings.setOnClickListener {
            //fragment settings
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
