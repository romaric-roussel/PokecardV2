package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.Model.retrofit.ResultData
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewModel.PokemonViewModel
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import kotlinx.android.synthetic.main.fragment_all_pokemon.*





class FragmentAllPokemon : BaseFragment(),AllPokemonListeAdapter.AllPokemonListeAdapterClickListener {

    override fun onClick(dataPosition: Int, pokemon: ResultData) {

        pokemonViewModel.selectedPokemon = pokemon
        (activity as MainActivity).openFragment(FragmentAllPokemonDetail())
    }

    private lateinit var pokemonViewModel: PokemonViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpRecyclerView()
        pokemonViewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)

        rv_pokemon_fragment.layoutManager = LinearLayoutManager(activity)
        val adapter = AllPokemonListeAdapter(this)
        rv_pokemon_fragment.adapter = adapter
        pokemonViewModel.allPokemonLiveData.observe(this, Observer {
            adapter.setData(it)
        })
        pokemonViewModel.fetchAllPokemon(context!!)

    }

}