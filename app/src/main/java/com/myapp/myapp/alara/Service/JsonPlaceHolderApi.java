package com.myapp.myapp.alara.Service;
import com.myapp.myapp.alara.model.*;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("cancion")
    Call<List<Cancion>>getCancion();

    @GET("discrografica")
    Call<List<Discografica>>getDiscrografica();

    @GET("disco")
    Call<List<Disco>>getDisco();
    @GET("artista")
    Call<List<Artista>>getArtista();
    @GET("Genero")
    Call<List<Genero>>getGenero();

}
