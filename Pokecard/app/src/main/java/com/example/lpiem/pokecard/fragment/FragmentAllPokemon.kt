package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.MainActivity
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import kotlinx.android.synthetic.main.fragment_all_pokemon.*
import java.util.ArrayList


class FragmentAllPokemon : Fragment() {

    lateinit var rvAllPokemon: RecyclerView
    lateinit var adapter: AllPokemonListeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
        adapter = AllPokemonListeAdapter(MainActivity().listeAllPokemon,(MainActivity().context ))

        //adapter.notifyDataSetChanged()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvAllPokemon = view.findViewById(R.id.rv_pokemon_fragment)
        rvAllPokemon.adapter = adapter



    }

    companion object {
        fun newInstance(): FragmentAllPokemon = FragmentAllPokemon()
    }


}