package com.example.lpiem.pokecard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.UserViewModel
import com.example.lpiem.pokecard.adapter.ExchangeAdapter
import com.example.lpiem.pokecard.data.model.UserExchangePokemon
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.data.model.UserPokemonResult
import kotlinx.android.synthetic.main.fragment_exchange.*


class FragmentExchangeMoi : BaseFragment(),ExchangeAdapter.ExchangeAdapterAdapterClickListener {
    override fun onClick(dataPosition: Int, pokemon: UserExchangePokemon) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var userViewModel: UserViewModel
    private lateinit var viewDialog : ViewDialog
    private lateinit var adapter : ExchangeAdapter
    private lateinit var resultDataObserver: Observer<List<UserExchangePokemon>>


    companion object {
        const val TAG = "FRAGMENTEXCHANGEMOI"
        fun newInstance(): FragmentExchangeMoi = FragmentExchangeMoi()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        rv_pokemon_fragment_user.layoutManager = LinearLayoutManager(activity)
        adapter = ExchangeAdapter(this)
        rv_pokemon_fragment_user.adapter=adapter
        resultDataObserver = Observer {
            adapter.setData(it)
        }
        val sharedPref = activity?.getSharedPreferences(resources.getString(R.string.sharePrefName), Context.MODE_PRIVATE) ?: return
        val displayId = sharedPref.getString(resources.getString(R.string.keyId),"")
        userViewModel.listUserPok(displayId!!).observe(this, resultDataObserver)



    }


}