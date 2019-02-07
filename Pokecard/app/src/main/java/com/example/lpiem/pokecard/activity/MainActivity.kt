package com.example.lpiem.pokecard.activity


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity: AppCompatActivity() {




    lateinit var fragmentAllPokemon: FragmentAllPokemon
    lateinit var fragmentAllUserPokemon: FragmentAllUserPokemon
             var fragmentAllPokemonDetail: FragmentAllPokemonDetail? =null
    lateinit var fragmentProfile: FragmentProfile
    //lateinit var fragmentConnexion: FragmentConnexion
    lateinit var button: Button
    lateinit var toolbar: ActionBar
    lateinit var CompteNom:String
    lateinit var CompteMail:String
    lateinit var CompteImage:String
    lateinit var bottomNavigation: BottomNavigationView




   override fun onCreate(savedInstanceState: Bundle?) {

       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentAllPokemon = FragmentAllPokemon()
        fragmentAllUserPokemon = FragmentAllUserPokemon()
        fragmentAllPokemonDetail = FragmentAllPokemonDetail()
        setDefaultFragment(fragmentAllPokemon)
        fragmentProfile = FragmentProfile()
        //fragmentConnexion = FragmentConnexion()
       /* CompteNom=intent.getStringExtra("nom")
        CompteMail=intent.getStringExtra("mail")
        CompteImage=intent.getStringExtra("photo")*/
        setDefaultFragment(FragmentAllUserPokemon())
        toolbar = supportActionBar!!
        bottomNavigation = findViewById(R.id.navigationView)
        bottomNavigation.selectedItemId = R.id.pokedex
        bottomNavigation.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private fun setDefaultFragment(defaultFragment: Fragment) {

        openFragment(defaultFragment)

    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.myPokemon -> {
                toolbar.title = getString(R.string.Mes_pokemon)
                openFragment(fragmentAllUserPokemon)
                return@OnNavigationItemSelectedListener true
            }

            R.id.pokedex -> {
                toolbar.title = getString(R.string.pokedex)
                openFragment(fragmentAllPokemon)
                return@OnNavigationItemSelectedListener true
            }

            R.id.settings -> {
                toolbar.title = getString(R.string.parametre)
                openFragment(fragmentProfile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

     fun openFragment(fragment: Fragment) {
        if(!isFinishing){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }


}
