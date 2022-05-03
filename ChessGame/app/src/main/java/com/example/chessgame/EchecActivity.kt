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
        var container1 = findViewById<FragmentContainerView>(R.id.fragment_container1)
        var container2 = findViewById<FragmentContainerView>(R.id.fragment_container2)
        container1.visibility = View.GONE
        container2.visibility = View.GONE
        val cimFragment = CimFragment()
        val setFragment = SetFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container1,cimFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        /*transaction.replace(R.id.fragment_container2,setFragment)
        transaction.addToBackStack(null)
        transaction.commit()*/
        var hide_fragcim = true
        var hide_fragset = true
        val PlayerNameWhite = intent.getStringExtra("Nom du joueur (blancs):")
        val PlayerNameBlack = intent.getStringExtra("Nom du joueur (noirs):")
        val JoueurBlanc = Joueur(PlayerNameWhite, "white")
        val JoueurNoir = Joueur(PlayerNameBlack, "black")

        tombstone.setOnClickListener {
            //partie pour le fragment
            var reset = drawingView.getCimReset()
            var piece_cimetiere: MutableList<Piece> = drawingView.getCimetiere()
            var truc:View? = cimFragment.view
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

    override fun onPause() {
        super.onPause()
        drawingView.pause()
    }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
    }
}
