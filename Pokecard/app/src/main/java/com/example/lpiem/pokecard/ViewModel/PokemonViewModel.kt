package com.example.lpiem.pokecard.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.data.model.*
import com.example.lpiem.pokecard.data.repository.PokemonRepository
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    private var allPokemonLiveData = MutableLiveData<List<ResultData>>()

    private val state = MutableLiveData<AllPokemonState>()



    //var pokemonLiveData = MutableLiveData<ResultOnePokemonData>()

    var selectedPokemon: ResultData? = null

    var pokemon: ResultOnePokemonData? = null



    fun fetchAllPokemon() {
        allPokemonLiveData = PokemonRepository.fetchAllPokemon()
    }

    fun getState(): LiveData<AllPokemonState> {
        return state
    }
    fun getAllPokemonLiveData(): MutableLiveData<List<ResultData>> {
        return allPokemonLiveData
    }









}