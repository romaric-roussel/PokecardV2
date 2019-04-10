package com.example.lpiem.pokecard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.data.model.UserExchangePokemon
import kotlinx.android.synthetic.main.item_exchange.view.*
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.fragment.FragmentExchangeMoi

class ExchangeAdapterMe (private val clickListener: ExchangeAdapterAdapterClickListener?) : RecyclerView.Adapter<ExchangeAdapterMe.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(position, allExchangeMe[position])
        }
        holder.bind(allExchangeMe[position])
    }

    //voir mutable live data
    //private var allExchange = emptyList<UserExchangePokemon>()
    private var allExchangeMe = emptyList<UserExchangePokemon>()
    //private var allExchangeFriend = MutableLiveData<UserExchangePokemon>()

    override fun getItemCount(): Int {
        return allExchangeMe.size
    }

    //ici aussi
    fun setData(myExchangeList: List<UserExchangePokemon>) {
        this.allExchangeMe = myExchangeList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){

        fun bind(user: UserExchangePokemon) {

            //v.tv_id_item_pokemon.text = pokemon?.idAmis.toString()
            v.tonpok.setText(user?.name_pokemon_utilisateur_1)
            v.sonpok.setText(user?.name_pokemon_utilisateur_2)
            //    Glide.with(v.iv_item_pokemon).load(pokemon?.image).into(v.iv_item_pokemon)


        }
    }

    interface ExchangeAdapterAdapterClickListener {
        fun onClick(dataPosition: Int, pokemon: UserExchangePokemon)
    }
}