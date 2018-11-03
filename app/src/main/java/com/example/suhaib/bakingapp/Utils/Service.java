package com.example.suhaib.bakingapp.Utils;

import com.example.suhaib.bakingapp.JsonFiles.Baking;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    String BaseURL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    @GET("baking.json")
    Call<ArrayList<Baking>>method();
}
