package com.example.chessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class MainActivity: AppCompatActivity() {

    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById<DrawingView>(R.id.vMain)

        var tombstone = findViewById<ImageView>(R.id.tombstone)
        var container = findViewById<FragmentContainerView>(R.id.fragment_container)
        var hide_frag = false

        tombstone.setOnClickListener {
            val imageFragment = ImageFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()

            transaction.replace(R.id.fragment_container,imageFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            if (hide_frag){
                container.setVisibility(View.VISIBLE)
                hide_frag = false
            }
            else{
                container.setVisibility(View.GONE)
                hide_frag = true
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
