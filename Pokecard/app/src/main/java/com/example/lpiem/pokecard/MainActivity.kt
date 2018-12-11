package com.example.lpiem.pokecard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.facebook.all.All
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


import java.util.ArrayList
import javax.security.auth.callback.Callback


class MainActivity() : AppCompatActivity() {

    val api by lazy {
        GestionRetrofit.initRetrofit()
    }

    internal lateinit var liste: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lstItems = ArrayList<String>()
        liste = findViewById(R.id.listView)
        doAsync {
            var allResult = api.getListPokemon().execute().body()
            uiThread {
                var size = allResult?.result?.data!!.size
                for (i in 0..size-1){
                    lstItems.add(allResult.result.data[i].toString());
                }
                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, lstItems)
                liste.adapter = adapter
               
            }
        }




    }


}
