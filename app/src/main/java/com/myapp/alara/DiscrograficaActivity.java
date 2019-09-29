package com.myapp.alara;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.myapp.alara.Service.*;
import com.myapp.myapp.alara.model.Discografica;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscrograficaActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discografica_layout);

        textViewResult=findViewById((R.id.text_view_result));
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://localhost:8097/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Discografica>> call = jsonPlaceHolderApi.getDiscrografica();
        call.enqueue(new Callback<List<Discografica>>() {
            @Override
            public void onResponse(Call<List<Discografica>> call, Response<List<Discografica>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
                List<Discografica>discograficas = response.body();
                for(Discografica discografica:discograficas){
                    String content ="";
                    content += "Id: "+discografica.getId()+ "\n";
                    content += "Pais: "+discografica.getPais()+ "\n";
                    content += "Correo: "+discografica.getCorreoEmpresarial()+ "\n";



                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Discografica>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });



    }
}
