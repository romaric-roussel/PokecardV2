package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.pokecard.R

class FragmentExchangeAmi : BaseFragment() {

    companion object {
        const val TAG = "FRAGMENTEXCHANGEAMI"
        fun newInstance(): FragmentExchangeAmi = FragmentExchangeAmi()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

}