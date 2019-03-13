package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.RecyclerTouchListener
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.adapter.AllPokemonUserListeAdapter
import java.util.*

class FragmentAllUserPokemon : BaseFragment() {

    lateinit var rvPokemonUser: RecyclerView
    lateinit var adapter: AllPokemonListeAdapter

    //var tabPokemon = arrayListOf<Pokemon>(pokemon1,pokemon2,pokemon3)
    //var listeAllUserPokemon: ArrayList<Pokemon> = ArrayList(tabPokemon)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_pokemon, container, false)

        rvPokemonUser = view.findViewById(R.id.rv_pokemon_fragment_user)
        //rvPokemonUser.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        //adapter = AllPokemonListeAdapter(listeAllUserPokemon)
        //rvPokemonUser.adapter = adapter
        //adapter.notifyDataSetChanged()
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {



        rvPokemonUser.addOnItemTouchListener(RecyclerTouchListener(
                activity!!.applicationContext,
                rvPokemonUser,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        var fragment =  (activity as MainActivity).fragmentAllPokemonDetail
                        //fragment?.pokemonId = listeAllUserPokemon[position].id_pokemon
                        (activity as MainActivity).openFragment(fragment!!)
                    }

                    override fun onLongClick(view: View?, position: Int) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                }))
    }


    companion object {
        fun newInstance(): FragmentAllUserPokemon = FragmentAllUserPokemon()
    }
}