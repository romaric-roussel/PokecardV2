package com.example.lpiem.pokecard;

import java.util.List;

import retrofit2.*;
import retrofit2.http.*;

public interface API {

    String url= "http://10.0.2.2/Pokecard/api/Pokemon/";
    @GET("read.php")
    Call <Example>getData();




}