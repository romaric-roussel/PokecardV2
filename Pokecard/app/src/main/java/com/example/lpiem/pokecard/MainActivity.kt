package com.example.lpiem.pokecard

import android.os.Bundle
import android.view.View


import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.fragment.*

//import kotlinx.android.synthetic.main.activity_display_pokemon.view.*

import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.view.*


import java.util.ArrayList


class MainActivity: AppCompatActivity() {


    /*val api by lazy {
        GestionRetrofit.initRetrofit()
    }*/

    lateinit var fragmentAllPokemon: FragmentAllPokemon
    lateinit var fragmentAllUserPokemon: FragmentAllUserPokemon
    lateinit var fragmentAllPokemonDetail: FragmentAllPokemonDetail
    lateinit var fragmentProfile: FragmentProfile
    lateinit var fragmentConnexion: FragmentConnexion

    lateinit var button: Button
    lateinit var button2 : Button
    lateinit var toolbar: ActionBar
    //lateinit var mAuth: FirebaseAuth
    //lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    lateinit var bottomNavigation: BottomNavigationView
    var listeAllPokemon: ArrayList<String> = ArrayList()



   override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentAllPokemon = FragmentAllPokemon.newInstance()
        fragmentAllUserPokemon = FragmentAllUserPokemon.newInstance()
        fragmentAllPokemonDetail = FragmentAllPokemonDetail.newInstance()
        fragmentAllPokemonDetail = FragmentAllPokemonDetail.newInstance()
        fragmentProfile = FragmentProfile()
        fragmentConnexion = FragmentConnexion()

        setDefaultFragment(fragmentConnexion)

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
        //Log.d("POKEMON", listeAllPokemon[0])
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
        //Log.d("POKEMON", fragmentAllPokemon.adapter.list[0]);


    }
   /* fun populateListeAllPokemon() {
        doAsync {
            var allResult = api.getListPokemon().execute().body()
            uiThread { _ ->
                var size = allResult?.result?.data!!.size
                for (i in 0..size - 1) {
                    listeAllPokemon.add(allResult.result.data[i].toString())

                    //Log.d("POKEMON", allResult.result.data[i].toString())
                }
                //Log.d("POKEMON", listeAllPokemon[0])
                fragmentAllPokemon.rvAllPokemon.adapter = AllPokemonListeAdapter(listeAllPokemon)


                //onComplete { FragmentAllPokemon().adapter.notifyDataSetChanged() }

                //val test=liste.adapter
                //rvAllPokemon.adapter = AllPokemonListeAdapter(listeAllPokemon, applicationContext)
                //test?.notifyDataSetChanged()

            }


        }
    }*/






    private fun setDefaultFragment(defaultFragment: Fragment) {
        openFragment(defaultFragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.myPokemon -> {

                toolbar.title = "UserList"
                openFragment(fragmentAllUserPokemon)

                return@OnNavigationItemSelectedListener true
            }
            R.id.pokedex -> {
                toolbar.title = "Pokedex"
                openFragment(fragmentAllPokemon)
                return@OnNavigationItemSelectedListener true
            }
            R.id.settings -> {
                toolbar.title = "Paramètres"
                openFragment(fragmentProfile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

     fun openFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }






}
