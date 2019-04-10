package com.example.lpiem.pokecard.data.model

data class UserOneAmi(val idAmis: Int,val id_liste_amis: Int,
                      val nom: String, val prenom: String, val mail: String,
                      val photo: String) {

    override fun toString(): String {
        return "UserOneAmi(val idAmis='$idAmis',id='$id_liste_amis', nom='$nom'," +
                " prenom='$prenom',mail='$mail', photo='$photo',photo='$photo')"
    }
}