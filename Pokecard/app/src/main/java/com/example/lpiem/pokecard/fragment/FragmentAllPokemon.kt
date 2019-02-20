package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.Model.retrofit.ResultData
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.AllPokemonViewModel
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import kotlinx.android.synthetic.main.fragment_all_pokemon.*





class FragmentAllPokemon : BaseFragment(),AllPokemonListeAdapter.AllPokemonListeAdapterClickListener {

    override fun onClick(dataPosition: Int, pokemon: ResultData) {

        allPokemonViewModel.selectedPokemon = pokemon
        (activity as MainActivity).openFragment(FragmentAllPokemonDetail())
    }

    private lateinit var allPokemonViewModel: AllPokemonViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpRecyclerView()
        allPokemonViewModel = ViewModelProviders.of(activity!!).get(AllPokemonViewModel::class.java)


        /*var viewDialog =  ViewDialog()
        viewDialog.ViewDialog(context!!)
        viewDialog.showDialog()

        val handler = Handler()
        handler.postDelayed(Runnable {

            viewDialog.hideDialog()
        }, 5000)*/



        rv_pokemon_fragment.layoutManager = LinearLayoutManager(activity)
        val adapter = AllPokemonListeAdapter(this)
        rv_pokemon_fragment.adapter = adapter
        allPokemonViewModel.allPokemonLiveData.observe(this, Observer {
            adapter.setData(it)
        })
        allPokemonViewModel.fetchAllPokemon(context!!)

    }

}