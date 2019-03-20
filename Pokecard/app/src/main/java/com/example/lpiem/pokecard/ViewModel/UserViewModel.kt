package com.example.lpiem.pokecard.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.data.model.UserInscriptionResult
import com.example.lpiem.pokecard.data.model.UserResultData
import com.example.lpiem.pokecard.data.repository.UserRepository

class UserViewModel : ViewModel() {

    var userResultData = MutableLiveData<UserResultData>()



    fun getUser(mail: String, password: String) : MutableLiveData<UserResultData> {
         userResultData = UserRepository.fetchConnexionUser(mail,password)
         //return UserRepository.fetchConnexionUser(mail,password)
        return userResultData
    }


    fun newUser(nom: String, prenom: String, email: String, type_connexion:Int, photo:String, password: String, confirm: String) : MutableLiveData<UserInscriptionResult> {
        return UserRepository.fetchInscription(nom,prenom,email,type_connexion,photo,password,confirm)
    }

}