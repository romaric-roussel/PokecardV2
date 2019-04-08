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
    val userListAmis = MutableLiveData<List<UserListAmis>>()


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


    fun fetchListAmi(id:String): MutableLiveData<List<UserListAmis>> {
        val call = apiUser.listAmis(id)
        call.enqueue(object : Callback<UserListAmis>{
            override fun onFailure(call: Call<UserListAmis>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserListAmis>, response: Response<UserListAmis>) {
                if(response.isSuccessful){
                    userListAmis.postValue(response.body()?.data)
                }
            }

        })
        return userListAmis
    }

}