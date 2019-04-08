package com.example.lpiem.pokecard.retrofit.API

import com.example.lpiem.pokecard.data.model.*
import retrofit2.Call
import retrofit2.http.*


interface API {

    @GET("pokemon")
    fun getListPokemon() : Call<AllResult>

    @GET("pokemon/{id}")
    fun getOnePokemon(@Path("id") id: String) : Call<OneResult>

    @GET("/user/{id}/pokemon")
    fun getListUserPokemon(@Path("id") id: String) : Call<AllUserPokemonResult>

    @POST("user")
    @FormUrlEncoded
    fun newUser(
            @Field("nom") nom: String,
            @Field("prenom") prenom: String,
            @Field("email") email: String,
            @Field("type_connexion") type_connexion: Int,
            @Field("photo") photo: String,
            @Field("password") password: String) : Call<UserInscriptionResult>


    @POST("user/login")
    @FormUrlEncoded
    fun getUser(@Field("login") email: String,
                @Field("password") password: String): Call<UserResultData>


    @POST("user/forgetPassword")
    fun forgetPassword(@Field("email") email: String,
                       @Field("password") password: String,
                       @Field("query") query: String): Call<List<OneResult>>

    @POST("user/{id}/friends")
    @FormUrlEncoded
    fun listAmis(@Path("id") id: String): Call<UserListAmis>

}