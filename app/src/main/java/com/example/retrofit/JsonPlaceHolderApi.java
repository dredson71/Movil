package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {


    void setUrl(String url);

    @GET("cancion")
    Call<List<Cancion>> getCancion();

    @GET
    Call<Cancion> getCancionbyId(@Url String url);

    @GET("artista")
    Call<List<Autor>> getArtista();

    @GET("discografica")
    Call<List<Discografica>> getDiscografica();

}
