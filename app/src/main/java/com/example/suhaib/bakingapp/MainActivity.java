package com.example.suhaib.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.suhaib.bakingapp.JsonFiles.Baking;
import com.example.suhaib.bakingapp.Utils.Service;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Baking> bakingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBakingObj();
            //Toast.makeText(MainActivity.this, bakingsList.size()+"k", Toast.LENGTH_LONG).show();
    }

    public void getBakingObj(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Service.BaseURL)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        Service service = retrofit.create(Service.class);
        service.method().enqueue(new Callback<ArrayList<Baking>>() {
            @Override
            public void onResponse(Call<ArrayList<Baking>> call, Response<ArrayList<Baking>> response) {
               // Toast.makeText(MainActivity.this,"Fetching Data success", Toast.LENGTH_LONG).show();
                bakingsList = response.body();
                //Toast.makeText(MainActivity.this,response.body().size()+"", Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, bakingsList.size()+"", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ArrayList<Baking>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }//end getBakingObj
}
