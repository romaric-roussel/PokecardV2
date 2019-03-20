package com.example.lpiem.pokecard.data.model

data class UserResultData (val code: Int,val id: String, val nom : String, val prenom :String,val mail: String,val photo :String,val status : String) {
    override fun toString(): String {
        return "UserResultData(val code='$code',id='$id', nom='$nom', prenom='$prenom',mail='$mail', photo='$photo',status='$status')"}
}