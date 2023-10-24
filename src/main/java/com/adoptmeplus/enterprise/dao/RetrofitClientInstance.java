package com.adoptmeplus.enterprise.dao;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The RetrofitClientInstance class is responsible for providing a single instance of Retrofit
 * for making HTTP requests to the JSON Schema API in the AdoptMePlus application.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static String BASE_URL = "http://json-schema.org";

    /**
     * Retrieves or creates a Retrofit instance for making HTTP requests.
     *
     * @return The Retrofit instance configured with the base URL and Gson converter factory.
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit==null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
