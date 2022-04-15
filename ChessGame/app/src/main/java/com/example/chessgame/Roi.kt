package com.example.chessgame

class Roi(position: Case, color: String): Piece(position, color) {
    var valeur = 100
    override fun bouger(newPos: Case){

    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}