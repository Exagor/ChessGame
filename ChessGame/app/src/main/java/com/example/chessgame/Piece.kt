package com.example.chessgame

abstract class Piece(var position: Case, val color: String){
    abstract fun bouger(newPos: Case)
    abstract fun mourir()


}