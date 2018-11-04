package com.example.suhaib.bakingapp;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suhaib.bakingapp.JsonFiles.Baking;
import com.example.suhaib.bakingapp.Utils.Adapter;
import com.example.suhaib.bakingapp.Utils.Service;
import com.example.suhaib.bakingapp.Utils.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Baking> bakingsList = new ArrayList<>();
    private RecyclerView recyclerView;
    int mNoOfColumns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());
        getBakingObj();
    }//end on create

    public void getBakingObj(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Service.BaseURL)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        Service service = retrofit.create(Service.class);
        service.method().enqueue(new Callback<ArrayList<Baking>>() {
            @Override
            public void onResponse(Call<ArrayList<Baking>> call, Response<ArrayList<Baking>> response) {
               Toast.makeText(MainActivity.this,"Fetching Data success", Toast.LENGTH_SHORT).show();
                bakingsList = response.body();

                recyclerView = findViewById(R.id.recycler_view);
                Adapter bakingAdapter= new Adapter(MainActivity.this,bakingsList);
                recyclerView.setAdapter(bakingAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<ArrayList<Baking>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_LONG).show();
            }
        });
    }//end getBakingObj

}
