package com.example.lpiem.pokecard.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_authentification.*

class Authentification : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)

        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentSignIn() , " Mot de passe oublié ")
        adapter.addFragment(FragmentSignUp() , " S'inscrire ")
        vp_sign_authentification_fragment.adapter = adapter
        tl_sign_authentification_fragment.setupWithViewPager(vp_sign_authentification_fragment)

    }
    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager){

        private val fragmentList : MutableList<Fragment> = ArrayList()
        private val titleList : MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment,title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

}