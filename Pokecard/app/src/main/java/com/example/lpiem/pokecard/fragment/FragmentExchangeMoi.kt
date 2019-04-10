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
import com.example.lpiem.pokecard.data.model.AllPokemonState
import kotlinx.android.synthetic.main.fragment_exchange.*


class FragmentExchangeMoi : BaseFragment(),ExchangeAdapterMe.ExchangeAdapterAdapterClickListener {
    override fun onClick(dataPosition: Int, user: UserExchangePokemon) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var userViewModel: UserViewModel
    private lateinit var viewDialog : ViewDialog
    private lateinit var adapterMe : ExchangeAdapterMe
    private lateinit var resultDataObserver: Observer<List<UserExchangePokemon>>
    private lateinit var stateObserver: Observer <AllPokemonState>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDialog =  ViewDialog()
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        rv_pokemon_fragment_user.layoutManager = LinearLayoutManager(activity)
        adapterMe = ExchangeAdapterMe(this)
        rv_pokemon_fragment_user.adapter=adapterMe
        resultDataObserver = Observer {
            adapterMe.setData(it)
        }
        stateObserver = Observer {
            handleState(it)
        }
        val sharedPref = activity?.getSharedPreferences(resources.getString(R.string.sharePrefName), Context.MODE_PRIVATE) ?: return
        val userId = sharedPref.getString(resources.getString(R.string.keyId),"")
        userViewModel.exchangeUserPokemonList(userId!!).observe(this, resultDataObserver)
        userViewModel.state.observe(this, stateObserver)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        userViewModel.state.removeObserver(stateObserver)
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