package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.UserViewModel
import com.example.lpiem.pokecard.adapter.ExchangeAdapterFriends
import com.example.lpiem.pokecard.adapter.ExchangeAdapterMe
import com.example.lpiem.pokecard.data.model.UserExchangePokemon
import kotlinx.android.synthetic.main.fragment_exchange.*
import kotlinx.android.synthetic.main.fragment_exchange_friend.*


class FragmentExchangeAmi : BaseFragment(), ExchangeAdapterFriends.ExchangeAdapterAdapterClickListener {
    override fun onClick(dataPosition: Int, pokemon: UserExchangePokemon) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var userViewModel: UserViewModel
    private lateinit var viewDialog : ViewDialog
    private lateinit var adapter : ExchangeAdapterFriends
    private lateinit var resultDataObserver: Observer<List<UserExchangePokemon>>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange_friend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        rv_pokemon_exchange_friend.layoutManager = LinearLayoutManager(activity)
        adapter = ExchangeAdapterFriends(this)
        rv_pokemon_fragment_user.adapter=adapter
        resultDataObserver = Observer {
            adapter.setData(it)
        }
        Log.d("IDAMI",  userViewModel.selectedIdAmi.toString())

        userViewModel.exchangeFriendPokemonList(userViewModel.selectedIdAmi.toString()!!).observe(this, resultDataObserver)



    }


}