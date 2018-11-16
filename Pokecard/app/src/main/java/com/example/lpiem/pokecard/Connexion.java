package com.example.lpiem.pokecard;

//3gm6wp3jThW8ssPN1P8ULbiBTrc=

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Connexion extends AppCompatActivity implements View.OnClickListener{


    private SignInButton signin;
    private Button signout;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN=9001;
    private TextView txtEmail,txtBirthday,txtFriends;
    private ProgressDialog mDialog;
    private ImageView imgAvatar;
    private GoogleApiClient googleApiClient;
    GoogleSignInOptions signInOptions=
            new GoogleSignInOptions.
                    Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail().build();
    googleApiClient=new GoogleApiClient.Builder(this)
                    .enableAutoManage(this,this).addApi
                    (Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

    ImageView avatar;




    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        //GOOGLE


        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
        findViewById(R.id.pok).setVisibility(View.INVISIBLE);
        signin=findViewById(R.id.sign_in_button);
        signout=findViewById(R.id.button2);
        Button pokemon=findViewById(R.id.pok);

        signin.setOnClickListener(this);
        signout.setOnClickListener(this);
        pokemon.setOnClickListener(this);



        /*---------------------------------------------------------*/

        //FACEBOOK
        callbackManager = CallbackManager.Factory.create();


        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        if (isLoggedIn){
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
            Log.d("connexion","connexion reussie");
            txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
            findViewById(R.id.button2).setVisibility(View.INVISIBLE);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.avatar).setVisibility(View.INVISIBLE);
            findViewById(R.id.txtFriends).setVisibility(View.INVISIBLE);
            findViewById(R.id.txtBirthday).setVisibility(View.INVISIBLE);
            findViewById(R.id.txtEmail).setVisibility(View.INVISIBLE);

        }

        else{

            LoginManager.getInstance().logOut();
            Log.d("deconnexion","deconnexion reussie");

        }

        /*super.onActivityResult(requestCode, resultCode, data);*/
        callbackManager.onActivityResult(requestCode, resultCode, data);

        txtBirthday=findViewById(R.id.txtBirthday);
        txtEmail=findViewById(R.id.txtEmail);
        txtFriends=findViewById(R.id.txtFriends);
        imgAvatar=findViewById(R.id.avatar);

        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));

        ;



        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {

            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount o) {


    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();



        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Connexion.this);

        if (acct != null) {
            txtEmail.setText(acct.getEmail());

                /*String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();*/

        }

        startActivityForResult(signInIntent, RC_SIGN_IN);
        findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.button2).setVisibility(View.VISIBLE);
        findViewById(R.id.login_button).setVisibility(View.INVISIBLE);
    }

    private void signOut() {


        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        findViewById(R.id.login_button).setVisibility(View.VISIBLE);
                        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
                        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);

                    }
                });
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


            case R.id.login_button:
                Log.d("fb", "onClick: ");
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        mDialog=new ProgressDialog(Connexion.this);
                        mDialog.setMessage("Elle a la tete de nabil fekir et elle fait des shootings");
                        mDialog.show();
                        findViewById(R.id.button2).setVisibility(View.INVISIBLE);
                        findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);

                        String accestoken= loginResult.getAccessToken().getToken();
                        GraphRequest request= GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                if(object==null){

                                    txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
                                    findViewById(R.id.button2).setVisibility(View.INVISIBLE);
                                    findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
                                    findViewById(R.id.avatar).setVisibility(View.INVISIBLE);
                                    findViewById(R.id.txtFriends).setVisibility(View.INVISIBLE);
                                    findViewById(R.id.txtBirthday).setVisibility(View.INVISIBLE);
                                    findViewById(R.id.txtEmail).setVisibility(View.INVISIBLE);

                                }
                                else{
                                    mDialog.dismiss();

                                    getData(object);
                                    Log.d("response:",response.toString());




                                }
                            }});


                        //requete

                        Bundle parameters= new Bundle();
                        parameters.putString("fields","id,email,birthday,friends");
                        request.setParameters(parameters);
                        request.executeAsync();




                    }

                    @Override
                    public void onCancel() {


                        Log.d("response","ca passe");

                    }



                    @Override

                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        }

    }

    private void getData(JSONObject object) {
        try{
            URL profile_picture=new URL("https://graph.facebook.com/"+object.getString("id") +"/picture?width=250&height=250");
            Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);



            txtEmail.setText(object.getString("email"));
            txtBirthday.setText(object.getString("birthday"));
            txtFriends.setText("friends:"+object.getJSONObject("friends").getJSONObject("summary").getString("total_count"));
            findViewById(R.id.pok).setVisibility(View.VISIBLE);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
