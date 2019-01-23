package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.Model.Pokemon
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.adapter.AllPokemonUserListeAdapter
import java.util.*

class FragmentAllUserPokemon : Fragment() {

    lateinit var rvPokemonUser: RecyclerView
    lateinit var adapter: AllPokemonListeAdapter
    var pokemon1 = Pokemon("25","pikachu","","","http://www.ray0.be/pokeapi/pokemon-img/fr/pikachu")
    var pokemon2 = Pokemon("9","tortank","","","http://www.ray0.be/pokeapi/pokemon-img/fr/tortank")
    var pokemon3 = Pokemon("13","aspicot","","","http://www.ray0.be/pokeapi/pokemon-img/fr/aspicot")
    var tabPokemon = arrayListOf<Pokemon>(pokemon1,pokemon2,pokemon3)
    var listeAllUserPokemon: ArrayList<Pokemon> = ArrayList(tabPokemon)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_pokemon, container, false)
        //listeAllUserPokemon.clear()
        //listeAllUserPokemon = tabPokemon
        rvPokemonUser = view.findViewById(R.id.rv_pokemon_fragment_user)
        rvPokemonUser.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = AllPokemonListeAdapter(listeAllUserPokemon)
        rvPokemonUser.adapter = adapter
        adapter.notifyDataSetChanged()
        return view

    }


    companion object {
        fun newInstance(): FragmentAllUserPokemon = FragmentAllUserPokemon()
    }
}