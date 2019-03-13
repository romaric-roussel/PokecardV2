package com.example.lpiem.pokecard.data.model

data class UserResultData (val id_utilisateur: String, val nom : String, val prenom :String,val photo :String) {
    override fun toString(): String {
        return "UserResultData(id_utilisateur='$id_utilisateur', nom='$nom', prenom='$prenom', photo='$photo')"}
}