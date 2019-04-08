package com.example.lpiem.pokecard.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.data.model.UserInscriptionResult
import com.example.lpiem.pokecard.data.model.UserListAmis
import com.example.lpiem.pokecard.data.model.UserResultData
import com.example.lpiem.pokecard.data.repository.UserRepository

class UserViewModel : ViewModel() {





    fun getUser(mail: String, password: String) : MutableLiveData<UserResultData> {
        return UserRepository.fetchConnexionUser(mail,password)
    }


    fun newUser(nom: String, prenom: String, email: String, type_connexion:Int, photo:String, password: String, confirm: String) : MutableLiveData<UserInscriptionResult> {
        return UserRepository.fetchInscription(nom,prenom,email,type_connexion,photo,password,confirm)
    }

    fun listAmis(id : String) : MutableLiveData<List<UserListAmis>>{

        return UserRepository.fetchListAmi(id)
    }

}