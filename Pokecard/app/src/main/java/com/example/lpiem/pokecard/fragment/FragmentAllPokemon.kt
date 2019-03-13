package com.example.lpiem.pokecard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.PokemonViewModel
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.data.model.AllPokemonState
import kotlinx.android.synthetic.main.fragment_all_pokemon.*





class FragmentAllPokemon : BaseFragment(),AllPokemonListeAdapter.AllPokemonListeAdapterClickListener {

    override fun onClick(dataPosition: Int, pokemon: ResultData) {

        pokemonViewModel.selectedPokemon = pokemon
        (activity as MainActivity).openFragment(FragmentAllPokemonDetail())
    }

    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var viewDialog : ViewDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpRecyclerView()
        viewDialog =  ViewDialog()
        pokemonViewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)

        rv_pokemon_fragment.layoutManager = LinearLayoutManager(activity)
        val adapter = AllPokemonListeAdapter(this)
        rv_pokemon_fragment.adapter = adapter
        pokemonViewModel.getAllPokemonLiveData().observe(this, Observer {
            adapter.setData(it)
        })
        pokemonViewModel.getState().observe(this, Observer { handleState(it) })
        pokemonViewModel.fetchAllPokemon()

    }

    private fun handleState(state: AllPokemonState?) {
        if(state?.isProgressLoadingShown == true){
            showLoadingProgress(context!!)
            return
        }
        if(state?.isProgressLoadingShown == false){
            hideLoadingProgress()
            return
        }
    }

    fun showLoadingProgress(context: Context){

        viewDialog.ViewDialog(context)
        viewDialog.showDialog()
    }

    fun hideLoadingProgress(){
        viewDialog.hideDialog()
    }



}