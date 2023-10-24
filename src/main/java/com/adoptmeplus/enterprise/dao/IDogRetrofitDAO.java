package com.adoptmeplus.enterprise.dao;
import com.adoptmeplus.enterprise.dto.Dog;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The IDogRetrofitDAO interface defines the contract for making HTTP requests to retrieve Dog entities
 * using Retrofit in the AdoptMePlus application.
 *
 * Implementing classes should use Retrofit annotations to define the HTTP endpoints for retrieving Dog records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public interface IDogRetrofitDAO {

    /**
     * Performs an HTTP GET request to retrieve a Dog record by its unique identifier.
     *
     * @param dogId The unique identifier of the Dog to be fetched.
     * @return A Retrofit Call object representing the HTTP request for retrieving a Dog.
     */
    @GET("/draft-07/schema#")
    Call<Dog> getDog(@Query("dogId")int dogId);
}
