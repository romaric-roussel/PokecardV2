/*package com.example.lpiem.pokecard

import android.content.Intent
import android.os.Bundle



import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

//import com.google.firebase.auth.FirebaseAuth

class Accueil : AppCompatActivity() {
    lateinit var button: Button
    //lateinit var mAuth: FirebaseAuth
    lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthStateListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)
        button=findViewById(R.id.btn_sedeconnecter)

        mAuth = FirebaseAuth.getInstance()


        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                val intent= Intent(this, Connexion::class.java)
                startActivity(intent)
            }
        }
        button.setOnClickListener { mAuth.signOut() }

    }


}
*/