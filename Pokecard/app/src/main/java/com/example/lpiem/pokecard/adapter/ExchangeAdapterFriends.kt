package com.example.lpiem.pokecard.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.data.model.UserExchangePokemon
import kotlinx.android.synthetic.main.item_exchange.view.*
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.fragment.FragmentExchangeMoi

class ExchangeAdapterFriends (private val clickListener: ExchangeAdapterAdapterClickListener?) : RecyclerView.Adapter<ExchangeAdapterFriends.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idAmis = idAmis
        holder.itemView.validate.setOnClickListener(View.OnClickListener { v->
            clickListener?.onClick(idAmis!!,getSharedPrefUserId(v.context),allExchangeFriend[position].id_pokemon_utilisateur_1.toString(),allExchangeFriend[position].id_pokemon_utilisateur_2.toString())
        })

        holder.bind(allExchangeFriend[position])
    }

    var idAmis :String? = null
    private var allExchangeFriend = emptyList<UserExchangePokemon>()


    override fun getItemCount(): Int {
        return allExchangeFriend.size
    }

    //ici aussi
    fun setData(friendExchangeList: List<UserExchangePokemon>) {
        this.allExchangeFriend = friendExchangeList
        notifyDataSetChanged()
    }
    fun getSharedPrefUserId(context: Context):String{
        val sharedPref = context.getSharedPreferences(context.resources.getString(R.string.sharePrefName), Context.MODE_PRIVATE)
        val userId = sharedPref.getString(context.resources.getString(R.string.keyId),"")
        return userId
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){
        var idAmis :String? = null
        fun bind(user: UserExchangePokemon) {

            //v.tv_id_item_pokemon.text = pokemon?.idAmis.toString()
            v.tonpok.setText(user?.name_pokemon_utilisateur_1)
            v.sonpok.setText(user?.name_pokemon_utilisateur_2)
            /*v.validate.setOnClickListener(View.OnClickListener { v->


                Log.d("lol",idAmis)
                Log.d("lol",getSharedPrefUserId(v.context))
                Log.d("lol",user.id_pokemon_utilisateur_1.toString())
                Log.d("lol",user.id_pokemon_utilisateur_2.toString())


            })*/
            //    Glide.with(v.iv_item_pokemon).load(pokemon?.image).into(v.iv_item_pokemon)


        }

    }
    interface ExchangeAdapterAdapterClickListener {
        fun onClick(idAmis:String,idUser:String,idPokemonUser1:String,idPokemonUser2: String)
    }
}