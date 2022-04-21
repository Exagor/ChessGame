package com.example.chessgame

 class Fou(position: Case, color: String): Piece(position, color) {
     override var image= if (color == "white") R.drawable.bishop_white else R.drawable.bishop_black
    override fun bouger(newPos: Case): Boolean{
        var fait = false
        return fait
    }

     override fun mourir() {
     }

 }