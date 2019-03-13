package com.example.lpiem.pokecard.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lpiem.pokecard.data.model.*
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PokemonRepository {

    var allPokemonLiveData = MutableLiveData<List<ResultData>>()
    var onePokemonLiveData = MutableLiveData<ResultOnePokemonData>()
    private val apiPokemon = GestionRetrofit.initRetrofit()
    lateinit var pokemonState : AllPokemonState
    val state = MutableLiveData<AllPokemonState>()


    fun fetchAllPokemon() : MutableLiveData<List<ResultData>> {

        val call = apiPokemon.getListPokemon()
        //showLoadingProgress(context)
        pokemonState = AllPokemonState(true)
        state.postValue(pokemonState)

        //Asynchrone
        call.enqueue(object : Callback<AllResult> {
            override fun onFailure(call: Call<AllResult>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                pokemonState.isProgressLoadingShown = false
                state.postValue(pokemonState)
                allPokemonLiveData.postValue(emptyList())

            }

            override fun onResponse(call: Call<AllResult>, response: Response<AllResult>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                pokemonState.isProgressLoadingShown = false
                state.postValue(pokemonState)

                if (response.isSuccessful) {
                    allPokemonLiveData.postValue(response.body()?.result?.data)

                } else {
                    allPokemonLiveData.postValue(emptyList())
                }
            }
        })
        return allPokemonLiveData
    }

    fun fetchOnePokemon(selectedPokemon : ResultData?) : MutableLiveData<ResultOnePokemonData> {

        val call = apiPokemon.getOnePokemon(selectedPokemon!!.id)
        val pokemonFailed = ResultOnePokemonData("0","failed","","","")

        pokemonState = AllPokemonState(true)
        state.postValue(pokemonState)

        //Asynchrone
        call.enqueue(object : Callback<OneResult> {
            override fun onFailure(call: Call<OneResult>, t: Throwable) {

                pokemonState.isProgressLoadingShown = false
                state.postValue(pokemonState)
                onePokemonLiveData.postValue(pokemonFailed)
            }

            override fun onResponse(call: Call<OneResult>, response: Response<OneResult>) {

                pokemonState.isProgressLoadingShown = false
                state.postValue(pokemonState)

                if (response.isSuccessful) {

                    onePokemonLiveData.postValue(response.body()?.result)

                } else {
                    onePokemonLiveData.postValue(pokemonFailed)
                }
            }
        })

        return onePokemonLiveData
    }




}