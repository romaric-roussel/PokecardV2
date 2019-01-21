package com.example.lpiem.pokecard

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.all.All
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_pokemon.view.*
import kotlinx.android.synthetic.main.item_pokemon.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


import java.util.ArrayList
import javax.security.auth.callback.Callback


class MainActivity() : AppCompatActivity() {

    val api by lazy {
        GestionRetrofit.initRetrofit()
    }

    internal lateinit var liste: RecyclerView
    lateinit var button: Button
    lateinit var button2 : Button
    lateinit var mAuth: FirebaseAuth
    lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthStateListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_pokemon)

       /* button2=findViewById(R.id.deco)*/

        mAuth = FirebaseAuth.getInstance()


        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                val intent= Intent(this, Connexion::class.java)
                startActivity(intent)
            }
        }
       /* button2.setOnClickListener { mAuth.signOut() }*/


        val lstItems= ArrayList<String> ()
        liste = findViewById(R.id.rv_pokeon_fragment)

        //val layoutManager = GridLayoutManager(this,2)

        val test=liste.adapter
        doAsync {
            var allResult = api.getListPokemon().execute().body()
            uiThread {
                var size = allResult?.result?.data!!.size
                for (i in 0..size-1){
                    lstItems.add(allResult.result.data[i].toString())

                    Log.d("POKEMON",allResult.result.data[i].toString())
                }
                //val test=liste.adapter
                liste.adapter = ListAdapter(lstItems,applicationContext)
                //test?.notifyDataSetChanged()

            }
            //test?.notifyDataSetChanged()
        }


       /* button.setOnClickListener {
            val intent = Intent(applicationContext, Connexion::class.java)
            startActivity(intent)
        }
*/



    }





}
