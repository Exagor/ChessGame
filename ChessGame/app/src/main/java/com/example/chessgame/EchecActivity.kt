package com.example.chessgame

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView

class EchecActivity: AppCompatActivity() {

    private lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_echec)
        drawingView = findViewById<DrawingView>(R.id.vMain)
        val tombstone = findViewById<ImageView>(R.id.tombstone)
        val settings = findViewById<ImageView>(R.id.settings_icon)
        val container1 = findViewById<FragmentContainerView>(R.id.fragment_container1)
        val container2 = findViewById<FragmentContainerView>(R.id.fragment_container2)
        container1.visibility = View.GONE
        container2.visibility = View.GONE
        val cimFragment = CimFragment()
        SetFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container1,cimFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        var hide_fragcim = true
        var hide_fragset = true
        // affichage des noms et création des joueurs
        val namePlayerWhite = intent.getStringExtra("joueur blanc")
        val namePlayerBlack = intent.getStringExtra("joueur noir")
        println("$namePlayerBlack + $namePlayerWhite")
        val JoueurBlanc = Joueur(namePlayerWhite, "white")
        val JoueurNoir = Joueur(namePlayerBlack, "black")
        val PlayerWhite = findViewById<TextView>(R.id.whitePlayer)
        val PlayerBlack = findViewById<TextView>(R.id.blackPlayer)
        PlayerWhite.text = namePlayerWhite
        PlayerBlack.text = namePlayerBlack


        tombstone.setOnClickListener {
            //partie pour le fragment
            val reset = drawingView.getCimReset()
            val piece_cimetiere: MutableList<Piece> = drawingView.getCimetiere()
            val truc:View? = cimFragment.view
            if (truc!=null){
                cimFragment.UpdateXml(piece_cimetiere, truc)
            }
            //partie pour afficher le cimetière
            if (hide_fragcim){
                container1.visibility = View.VISIBLE
                hide_fragcim = false
            }
            else{
                container1.visibility = View.GONE
                hide_fragcim = true
            }
            if (reset){
                cimFragment.reset()
            }
        }
        settings.setOnClickListener {
            //fragment settings
            if (hide_fragset){
                container2.visibility = View.VISIBLE
                hide_fragset = false
            }
            else{
                container2.visibility = View.GONE
                hide_fragset = true
            }
        }
    }
    fun ClickReset(){
        drawingView.newGame()
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
