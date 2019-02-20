package com.example.lpiem.pokecard.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.Model.Pokemon

class AllPokemonViewModel : ViewModel() {

    var allPokemonLiveData = MutableLiveData<List<Pokemon>>()

    var selectedPokemon: Pokemon? = null

    companion object {
        var magicCardsLiveData = MutableLiveData<List<Card>>()

        val apiCard = ApiFactory.myCardsApi
    }
}