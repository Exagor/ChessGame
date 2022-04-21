package com.example.chessgame

abstract class Piece(var position: Case, val color: String){
    abstract var image : Int
    abstract fun bouger(newPos: Case): Boolean
    abstract fun mourir()


}