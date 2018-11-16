package com.example.lpiem.pokecard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import com.facebook.login.widget.LoginButton;

public class ConnexionGoogle extends AppCompatActivity{
SignInButton button;
FirebaseAuth mAuth;
private final static int RC_SIGN_IN=2;
GoogleApiClient mGoogleApiClient;
FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        button=findViewById(R.id.sign_in_button);

        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(ConnexionGoogle.this,Accueil.class));
                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleApiClient= new GoogleApiClient.Builder(this)
                            .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                                @Override
                                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                    Toast.makeText(ConnexionGoogle.this,"Marche pas ",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                            .build();


    }

//    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build();


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(ConnexionGoogle.this,"Marche pas authentification",Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("auth", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("connex", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("con", "signInWithCredential:failure", task.getException());
                            Toast.makeText(ConnexionGoogle.this,"Marche pas authentification-connect",Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });
    }
}

































   /* @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        Ecran=findViewById(R.id.ecran);
        signin=findViewById(R.id.sign_in_button);
        signout=findViewById(R.id.button2);
        txtEmail=findViewById(R.id.txtEmail);
        txtBirthday=findViewById(R.id.txtBirthday);
        txtFriends=findViewById(R.id.txtFriends);
        imgAvatar=findViewById(R.id.avatar);





        signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
        findViewById(R.id.pok).setVisibility(View.INVISIBLE);

        pokemon=findViewById(R.id.pok);


        signin.setOnClickListener(this);
        signout.setOnClickListener(this);
        pokemon.setOnClickListener(this);


    }

    private void signIn() {
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,RC_SIGN_IN);

    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });

    }




    private void handleResult(GoogleSignInResult result){
        Log.d("handleresult1", String.valueOf(result));
        Log.d("ctn: ", "skut");
        Log.d("Status", String.valueOf(result.getStatus().getStatusMessage()));
        if(result.isSuccess()){
            Log.d("ctn: ", "skut");
            GoogleSignInAccount account=result.getSignInAccount();
            String name=account.getDisplayName();
            String mail=account.getEmail();
            String img_url=account.getPhotoUrl().toString();
            Log.d("Utilisateur: ", name+" "+mail);

            txtFriends.setText(name);
            txtEmail.setText(mail);
            Picasso.with(this).load(img_url).into(imgAvatar);
            updateUI(true);
        }
        else {updateUI(false);}
    }

    private void updateUI(boolean islogin) {
        if (islogin){

            findViewById(R.id.txtFriends).setVisibility(View.VISIBLE);
            findViewById(R.id.txtEmail).setVisibility(View.VISIBLE);
            signin.setVisibility(View.GONE);
            //loginButton.setVisibility(View.GONE);

        }
        else{
            findViewById(R.id.txtFriends).setVisibility(View.GONE);
            findViewById(R.id.txtEmail).setVisibility(View.GONE);
            signin.setVisibility(View.VISIBLE);
           // loginButton.setVisibility(View.VISIBLE);

        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.d("requestcode:issoufle", "onActivityResult: ");

            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);

            Log.d("resultat", String.valueOf(result));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.button2:
                signOut();

                break;
            // ...
            case R.id.pok:
                Intent intent =new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
*/