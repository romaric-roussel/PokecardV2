package com.example.lpiem.pokecard.Model

data class ResultData (val id: String, val nom : String, val url :String) {
    override fun toString(): String {
        return "$id  $nom"
    }
}


