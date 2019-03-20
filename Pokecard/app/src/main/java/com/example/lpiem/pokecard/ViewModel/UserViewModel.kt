package com.example.lpiem.pokecard.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.data.model.UserResultData
import com.example.lpiem.pokecard.data.repository.UserRepository

class UserViewModel : ViewModel() {





    fun getUser(mail: String, password: String) : MutableLiveData<UserResultData> {
        return UserRepository.fetchConnexionUser(mail,password)
    }

}