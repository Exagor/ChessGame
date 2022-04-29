package com.example.chessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cim)

        var piece0 = findViewById<ImageView>(R.id.piece0)
        var piece1 = findViewById<ImageView>(R.id.piece1)
        var piece2 = findViewById<ImageView>(R.id.piece2)
        var piece3 = findViewById<ImageView>(R.id.piece3)
        var piece4 = findViewById<ImageView>(R.id.piece4)
        var piece5 = findViewById<ImageView>(R.id.piece5)
        var piece6 = findViewById<ImageView>(R.id.piece6)
        var piece7 = findViewById<ImageView>(R.id.piece7)
        var piece8 = findViewById<ImageView>(R.id.piece8)
        var piece9 = findViewById<ImageView>(R.id.piece9)
    }
}