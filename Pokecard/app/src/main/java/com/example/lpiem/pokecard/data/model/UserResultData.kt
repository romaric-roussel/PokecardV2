package com.example.lpiem.pokecard.data.model

data class UserResultData (val id: String, val nom : String, val prenom :String,val mail: String,val photo :String) {
    override fun toString(): String {
        return "UserResultData(id='$id', nom='$nom', prenom='$prenom',mail='$mail', photo='$photo')"}
}