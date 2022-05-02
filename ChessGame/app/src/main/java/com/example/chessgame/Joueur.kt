package com.example.chessgame

class Joueur(nom: String?,color: String) {
    var score = 0
    fun updateScore(points: Int){
        score += points
    }
}