package com.example.lpiem.pokecard.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lpiem.pokecard.data.model.*
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    val userInscription = MutableLiveData<UserInscriptionResult>()
    val userConnexion= MutableLiveData<UserResultData>()
    private val apiUser = GestionRetrofit.initRetrofit()
    val userListAmis = MutableLiveData<List<UserOneAmi>>()
    val userListExchange = MutableLiveData<List<UserExchangePokemon>>()
    val exchangeResult = MutableLiveData<ExchangeResult>()


    fun fetchConnexionUser(mail:String,password:String): MutableLiveData<UserResultData> {
        val call = apiUser.getUser(mail,password)
        call.enqueue(object : Callback<UserResultData>{
            override fun onFailure(call: Call<UserResultData>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserResultData>, response: Response<UserResultData>) {
              if(response.isSuccessful){
                  userConnexion.postValue(response.body())
              }
            }

        })
        return userConnexion
}



    fun fetchInscription(nom: String, prenom: String, email: String, type_connexion:Int, photo:String, password: String, confirm: String): MutableLiveData<UserInscriptionResult>{

    val call= apiUser.newUser(nom,prenom,email,type_connexion,photo,password)

    call.enqueue(object : Callback<UserInscriptionResult>{
        override fun onFailure(call: Call<UserInscriptionResult>, t: Throwable) {

        }

        override fun onResponse(call: Call<UserInscriptionResult>, response: Response<UserInscriptionResult>) {
            userInscription.postValue(response.body())
        }

        })
    return userInscription
    }


    fun fetchListAmi(id:String): MutableLiveData<List<UserOneAmi>> {
        val call = apiUser.listAmis(id)
        call.enqueue(object : Callback<UserListAmiResult>{
            override fun onFailure(call: Call<UserListAmiResult>, t: Throwable) {
                userListAmis.postValue(emptyList())
            }

            override fun onResponse(call: Call<UserListAmiResult>, response: Response<UserListAmiResult>) {
                if(response.isSuccessful){
                    userListAmis.postValue(response.body()?.result!!.data)
                }
            }

        })
        return userListAmis
    }


    fun fetchListPokExchange(id:String): MutableLiveData<List<UserExchangePokemon>> {
        val call = apiUser.listPokUser(id)
        call.enqueue(object : Callback<UserExchangeResult>{
            override fun onFailure(call: Call<UserExchangeResult>, t: Throwable) {
                userListExchange.postValue(emptyList())
            }

            override fun onResponse(call: Call<UserExchangeResult>, response: Response<UserExchangeResult>) {
                if(response.isSuccessful){
                    userListExchange.postValue(response.body()?.result!!.data)
                }
            }

        })
        return userListExchange
    }

    fun doExchange(idUserSend : String,idUserReceive : String,idPokemonSend : String,idPokemonReceive : String): MutableLiveData<ExchangeResult> {
        val call = apiUser.exchangePokemon(idUserSend,idUserReceive,idPokemonSend,idPokemonReceive)
        call.enqueue(object : Callback<ExchangeResult>{
            override fun onFailure(call: Call<ExchangeResult>, t: Throwable) {
                exchangeResult.postValue(null)
            }

            override fun onResponse(call: Call<ExchangeResult>, response: Response<ExchangeResult>) {
                if(response.isSuccessful){
                    exchangeResult.postValue(response.body())
                }
            }

        })
        return exchangeResult
    }

}