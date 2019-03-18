package com.example.lpiem.pokecard.retrofit.API

import com.example.lpiem.pokecard.data.model.AllResult
import com.example.lpiem.pokecard.data.model.OneResult
import com.example.lpiem.pokecard.data.model.UserResult
import retrofit2.Call
import retrofit2.http.*


interface API {

    @GET("pokemon")
    fun getListPokemon() : Call<AllResult>

    @GET("pokemon/{id}")
    fun getOnePokemon(@Path("id") id: String) : Call<OneResult>


    @POST("user")
    @FormUrlEncoded
    fun newUser(
            @Field("nom") nom: String,
            @Field("prenom") prenom: String,
            @Field("email") email: String,
            @Field("type_connexion") type_connexion: Int,
            @Field("photo") photo: String,
            @Field("password") password: String) : Call<UserResult>


    @POST("user/login")
    @FormUrlEncoded
    fun getUser(@Field("login") email: String,
                @Field("password") password: String): Call<UserResult>


    @POST("user/forgetPassword")
    fun forgetPassword(@Field("email") email: String,
                       @Field("password") password: String,
                       @Field("query") query: String): Call<List<OneResult>>


}