package com.example.lpiem.pokecard.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.data.model.*
import com.example.lpiem.pokecard.data.repository.UserRepository

class UserViewModel : ViewModel() {


    var selectedIdAmi : Int? = null


    fun getUser(mail: String, password: String) : MutableLiveData<UserResultData> {
        return UserRepository.fetchConnexionUser(mail,password)
    }


    fun newUser(nom: String, prenom: String, email: String, type_connexion:Int, photo:String, password: String, confirm: String) : MutableLiveData<UserInscriptionResult> {
        return UserRepository.fetchInscription(nom,prenom,email,type_connexion,photo,password,confirm)
    }

    fun listAmis(id : String) : MutableLiveData<List<UserOneAmi>>{

        return UserRepository.fetchListAmi(id)
    }

    fun exchangeUserPokemonList(id : String) : MutableLiveData<List<UserExchangePokemon>>{

        return UserRepository.fetchListPokExchange(id)
    }

    fun exchangeFriendPokemonList(id : String) : MutableLiveData<List<UserExchangePokemon>>{

        return UserRepository.fetchListPokExchange(id)
    }

    fun exchangePokemon(idUserSend : String,idUserReceive : String,idPokemonSend : String,idPokemonReceive : String) : MutableLiveData<ExchangeResult>{

        return UserRepository.doExchange(idUserSend,idUserReceive,idPokemonSend,idPokemonReceive)
    }




}