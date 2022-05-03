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
        val cimFragment = CimFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container,cimFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        var reset = false
        var hide_fragcim = true
        var hide_fragset = true
        val PlayerNameWhite = intent.getStringExtra("Nom du joueur (blancs):")
        val PlayerNameBlack = intent.getStringExtra("Nom du joueur (noirs):")
        val JoueurBlanc = Joueur(PlayerNameWhite, "white")
        val JoueurNoir = Joueur(PlayerNameBlack, "black")

        tombstone.setOnClickListener {
            //partie pour le fragment
            reset = drawingView.getCimReset()
            var piece_cimetiere: MutableList<Piece> = drawingView.getCimetiere()
            var truc:View? = cimFragment.view
            if (truc!=null){
                cimFragment.UpdateXml(piece_cimetiere, truc)
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
            if (reset){
                cimFragment.reset()
            }
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
