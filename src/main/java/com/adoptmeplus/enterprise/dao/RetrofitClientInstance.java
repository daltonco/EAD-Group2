package com.adoptmeplus.enterprise.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static final String BASE_URL = "http://localhost:8080";
    private static Retrofit retrofit;

    private RetrofitClientInstance() {
        // Private constructor to prevent instantiation
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = createRetrofitInstance();
        }
        return retrofit;
    }

    private static Retrofit createRetrofitInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
