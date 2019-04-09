package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lpiem.pokecard.R

class FragmentExchangeMoi : BaseFragment() {
    companion object {
        const val TAG = "FRAGMENTEXCHANGEMOI"
        fun newInstance(): FragmentExchangeMoi = FragmentExchangeMoi()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

}