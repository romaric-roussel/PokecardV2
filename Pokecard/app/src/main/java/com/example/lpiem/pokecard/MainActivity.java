package com.example.lpiem.pokecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList <String> lstItems=new ArrayList<>();
        liste = findViewById(R.id.listView);



        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(GsonConverterFactory.create()).build();

        API api=retrofit.create(API.class);

        Call<Example> call =api.getData();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List <Result> pok=response.body().getData();


                for (Result result: pok
                        ) {
                    Log.d("Resultatpok", result.toString());
                    lstItems.add(result.toString());

                }

                for(int i=0; i<lstItems.size();i++){

                    Log.d("Pokemon: ", lstItems.get(i));

                }

               ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,lstItems);

                liste.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
