package com.example.lpiem.pokecard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.ViewModel.PokemonViewModel
import com.example.lpiem.pokecard.ViewModel.UserViewModel
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.adapter.AllUserFriend
import com.example.lpiem.pokecard.data.model.AllPokemonState
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.data.model.UserListAmis
import com.example.lpiem.pokecard.data.model.UserOneAmi
import kotlinx.android.synthetic.main.fragment_friend.*

class FragmentFriend : BaseFragment(),AllUserFriend.AllUserFriendsAdapterClickListener {
    override fun onClick(dataPosition: Int, user: UserOneAmi) {
        userViewModel.selectedIdAmi=user.idAmis
        (activity as MainActivity).openFragment(FragmentExchangeAmi())
    }


    private lateinit var userViewModel: UserViewModel
    private lateinit var viewDialog : ViewDialog
    private lateinit var adapter : AllUserFriend
    private lateinit var resultDataObserver: Observer<List<UserOneAmi>>
  //  private lateinit var stateObserver: Observer<AllPokemonState>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friend, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
        rv_pokemon_fragment_user_friend.layoutManager = LinearLayoutManager(activity)
        adapter = AllUserFriend(this)
        rv_pokemon_fragment_user_friend.adapter=adapter
        resultDataObserver = Observer {
            adapter.setData(it)
        }
        val sharedPref = activity?.getSharedPreferences(resources.getString(R.string.sharePrefName), Context.MODE_PRIVATE) ?: return
        val displayId = sharedPref.getString(resources.getString(R.string.keyId),"")
        userViewModel.listAmis(displayId!!).observe(this, resultDataObserver)



    }



}