package com.example.chessgame

import kotlin.reflect.typeOf

interface Roque {

     fun RoqueValide(caseRoi:Int, caseTour:Int, cases:MutableList<Case>): Boolean
    {   if (cases[caseTour].piece !is Tour) return false
        val from = if(caseRoi< caseTour)  caseRoi else caseTour
        val to = if( from == caseRoi) caseTour else caseRoi
        for (i in (from+1)..(to-1)){
                if (cases[i].piece != null){
                    val a = cases[i].piece!!.color
                    return false
                }
        }
        return true
    }

}