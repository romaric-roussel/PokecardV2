package com.example.lpiem.pokecard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.UserViewModel
import com.example.lpiem.pokecard.adapter.ExchangeAdapterMe
import com.example.lpiem.pokecard.data.model.UserExchangePokemon
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.fragment_exchange.*


class FragmentExchangeMoi : BaseFragment(),ExchangeAdapterMe.ExchangeAdapterAdapterClickListener {
    override fun onClick(dataPosition: Int, user: UserExchangePokemon) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var userViewModel: UserViewModel
    private lateinit var viewDialog : ViewDialog
    private lateinit var adapterMe : ExchangeAdapterMe
    private lateinit var resultDataObserver: Observer<List<UserExchangePokemon>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        rv_pokemon_fragment_user.layoutManager = LinearLayoutManager(activity)
        adapterMe = ExchangeAdapterMe(this)
        rv_pokemon_fragment_user.adapter=adapterMe
        resultDataObserver = Observer {
            adapterMe.setData(it)
        }
        val sharedPref = activity?.getSharedPreferences(resources.getString(R.string.sharePrefName), Context.MODE_PRIVATE) ?: return
        val userId = sharedPref.getString(resources.getString(R.string.keyId),"")
        userViewModel.exchangeUserPokemonList(userId!!).observe(this, resultDataObserver)


    }


}