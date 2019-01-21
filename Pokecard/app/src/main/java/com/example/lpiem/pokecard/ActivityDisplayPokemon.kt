/*
package com.example.lpiem.pokecard

import android.R
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class ActivityDisplayPokemon : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)
        val lstItems = ArrayList<String>()
        liste = findViewById(R.id.activity_display_pokemon)

        doAsync {
            var allResult = api.getListPokemon().execute().body()
            uiThread {
                var size = allResult?.result?.data!!.size
                for (i in 0..size-1){
                    lstItems.add(allResult.result.data[i].toString())
                }
                val adapter = ArrayAdapter(this@MainActivity, R.layout.simple_list_item_1, lstItems)
                liste.adapter = adapter

            }
        }

    }


}*/
