package com.example.lpiem.pokecard.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class Authentification : BaseActivity() {
    lateinit var FragmentSignIn: FragmentSignIn
    lateinit var FragmentSignUp: FragmentSignUp
    lateinit var bottomNavigation: TabLayout
    private lateinit var mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)
        FragmentSignIn = FragmentSignIn()
        FragmentSignUp = FragmentSignUp()
        setDefaultFragment(FragmentSignIn)



        mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.ti_signup_authentification_fragment -> {

                    openFragment(FragmentSignUp)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.ti_signin_authentification_fragment -> {
                    openFragment(FragmentSignIn)
                    return@OnNavigationItemSelectedListener true
                }


            }
            false
        }

    }

    private fun setDefaultFragment(defaultFragment: Fragment) {

        openFragment(defaultFragment)

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