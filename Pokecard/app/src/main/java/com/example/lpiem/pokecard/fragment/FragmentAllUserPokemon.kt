package com.example.lpiem.pokecard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.PokemonViewModel
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.adapter.AllPokemonUserListeAdapter
import com.example.lpiem.pokecard.data.model.AllPokemonState
import com.example.lpiem.pokecard.data.model.UserPokemonResultData
import kotlinx.android.synthetic.main.fragment_user_pokemon.*

class FragmentAllUserPokemon : BaseFragment(),AllPokemonUserListeAdapter.AllUserPokemonListeAdapterClickListener {

    override fun onClick(dataPosition: Int, pokemon: UserPokemonResultData) {

        pokemonViewModel.selectedUserPokemon = pokemon
        (activity as MainActivity).openFragment(FragmentAllUserPokemonDetail())
    }

    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var viewDialog: ViewDialog
    private lateinit var adapter: AllPokemonUserListeAdapter
    private lateinit var resultDataObserver: Observer<List<UserPokemonResultData>>
    private lateinit var stateObserver: Observer<AllPokemonState>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_pokemon, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpRecyclerView()
        viewDialog = ViewDialog()
        pokemonViewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)

        rv_pokemon_fragment_user.layoutManager = LinearLayoutManager(activity)
        adapter = AllPokemonUserListeAdapter(this)
        rv_pokemon_fragment_user.adapter = adapter
        resultDataObserver = Observer {
            adapter.setData(it)
        }
        stateObserver = Observer {
            handleState(it)
        }
        pokemonViewModel.getAllUserPokemonLiveData().observe(this, resultDataObserver)

        pokemonViewModel.state.observe(this, stateObserver)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        pokemonViewModel.state.removeObserver(stateObserver)
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