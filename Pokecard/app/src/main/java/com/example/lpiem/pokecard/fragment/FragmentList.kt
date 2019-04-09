package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.adapter.ExchangeViewPager
import kotlinx.android.synthetic.main.fragment_exchange_list.*

class FragmentList : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setupViewPager()

    }
    private fun setupViewPager() {
        val adapter = ExchangeViewPager(childFragmentManager)
        adapter.addFragment(FragmentExchangeMoi.newInstance(), getString(R.string.ma_liste))
        adapter.addFragment(FragmentExchangeAmi.newInstance(), getString(R.string.mes_amis))
        vp_exchange_fragment.adapter = adapter
        tl_exchange_fragment.setupWithViewPager(vp_exchange_fragment)
    }

}


//package com.example.lpiem.pokecard.activity
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import com.example.lpiem.pokecard.R
//import com.example.lpiem.pokecard.fragment.*
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.activity_authentification.*
//
//class Authentification : BaseActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_authentification)
//
//        val adapter = MyViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(FragmentSignIn() , " Mot de passe oublié ")
//        adapter.addFragment(FragmentSignUp() , " S'inscrire ")
//        vp_sign_authentification_fragment.adapter = adapter
//        tl_sign_authentification_fragment.setupWithViewPager(vp_sign_authentification_fragment)
//
//    }
//    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager){
//
//        private val fragmentList : MutableList<Fragment> = ArrayList()
//        private val titleList : MutableList<String> = ArrayList()
//
//        override fun getItem(position: Int): Fragment {
//            return fragmentList[position]
//        }
//
//        override fun getCount(): Int {
//            return fragmentList.size
//        }
//
//        fun addFragment(fragment: Fragment,title:String){
//            fragmentList.add(fragment)
//            titleList.add(title)
//        }
//
//        override fun getPageTitle(position: Int): CharSequence? {
//            return titleList[position]
//        }
//
//    }
//
//}