package com.example.lpiem.pokecard

import android.content.Context
import android.content.Intent

import android.os.Bundle


import android.util.Log

import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.fragment.FragmentAllPokemon
import com.example.lpiem.pokecard.fragment.FragmentAllUserPokemon

import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_all_pokemon.view.*
//import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.notificationManager
import org.jetbrains.anko.onComplete
import org.jetbrains.anko.uiThread


import java.util.ArrayList


class MainActivity : AppCompatActivity() {


    val api by lazy {
        GestionRetrofit.initRetrofit()
    }

    var context : Context = this
    lateinit var fragmentAllPokemon: FragmentAllPokemon
    lateinit var fragmentAllUserPokemon: FragmentAllUserPokemon
    lateinit var button: Button
    lateinit var button2 : Button
    lateinit var toolbar: ActionBar
    //lateinit var mAuth: FirebaseAuth
    //lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    lateinit var bottomNavigation: BottomNavigationView
    var listeAllPokemon: ArrayList<String> = ArrayList()


    override fun onStart() {
        super.onStart()
        //mAuth.addAuthStateListener(mAuthStateListener)

    }

    override fun onResume() {
        populateListeAllPokemon()
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentAllPokemon = FragmentAllPokemon()
        fragmentAllUserPokemon = FragmentAllUserPokemon()
        setDefaultFragment(fragmentAllPokemon)

        //rvAllPokemon = findViewById(R.id.rv_pokemon_fragment)
       // rvAllPokemon.layoutManager = LinearLayoutManager(this)
        //rvAllPokemon.adapter = AllPokemonListeAdapter(listeAllPokemon)

        toolbar = supportActionBar!!
        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.selectedItemId = R.id.pokedex
        bottomNavigation.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //mAuth = FirebaseAuth.getInstance()


       /* mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                val intent= Intent(this, Connexion::class.java)
                startActivity(intent)
            }
        }*/

        //populateListeAllPokemon()
       // (rvAllPokemon.adapter as AllPokemonListeAdapter).notifyDataSetChanged()
        //val layoutManager = GridLayoutManager(this,2)

        //val test=liste.adapter
      /*  doAsync {
            var allResult = api.getListPokemon().execute().body()
            uiThread {
                var size = allResult?.result?.data!!.size
                for (i in 0..size-1){
                    lstItems.add(allResult.result.data[i].toString())

                    Log.d("POKEMON",allResult.result.data[i].toString())
                }
                //val test=liste.adapter
                liste.adapter = AllPokemonListeAdapter(lstItems,applicationContext)
                //test?.notifyDataSetChanged()

            }
            //test?.notifyDataSetChanged()
        }*/


       /* button.setOnClickListener {
            val intent = Intent(applicationContext, Connexion::class.java)
            startActivity(intent)
        }
*/



    }

    private fun populateListeAllPokemon() {
        doAsync {
            var allResult = api.getListPokemon().execute().body()
            uiThread { it ->
                var size = allResult?.result?.data!!.size
                for (i in 0..size - 1) {
                    listeAllPokemon.add(allResult.result.data[i].toString())
                    FragmentAllPokemon().adapter.notifyDataSetChanged()
                    //Log.d("POKEMON", allResult.result.data[i].toString())
                }

                //onComplete { FragmentAllPokemon().adapter.notifyDataSetChanged() }

                //val test=liste.adapter
                //rvAllPokemon.adapter = AllPokemonListeAdapter(listeAllPokemon, applicationContext)
                //test?.notifyDataSetChanged()

            }

            
        }
    }



    private fun setDefaultFragment(defaultFragment: Fragment) {
        openFragment(defaultFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.myPokemon -> {

                toolbar.title = "UserList"
                //val userPokemonFragment = FragmentAllUserPokemon.newInstance()
                //openFragment(userPokemonFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.pokedex -> {
                toolbar.title = "Pokedex"
                val allPokemon = FragmentAllPokemon.newInstance()
                openFragment(allPokemon)
                return@OnNavigationItemSelectedListener true
            }
            R.id.settings -> {
                toolbar.title = "Paramètre"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }






}
