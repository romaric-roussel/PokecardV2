package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.adapter.AllPokemonUserListeAdapter
import java.util.*

class FragmentAllUserPokemon : Fragment() {

    lateinit var rvPokemonUser: RecyclerView
    lateinit var adapter: AllPokemonListeAdapter
    var pokemonUser = arrayOf("1 pikachu", "6 dracaufeu", "19 azumarille")
    var listeAllUserPokemon: ArrayList<String> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_pokemon, container, false)
        listeAllUserPokemon.clear()
        listeAllUserPokemon.addAll(pokemonUser)
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