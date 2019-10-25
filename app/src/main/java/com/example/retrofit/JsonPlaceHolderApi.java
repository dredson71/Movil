package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("cancion")
    Call<List<Cancion>> getCancion();


    @GET("artista")
    Call<List<Autor>> getArtista();
}
