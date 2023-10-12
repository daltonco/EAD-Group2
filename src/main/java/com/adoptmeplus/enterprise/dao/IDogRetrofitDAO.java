package com.adoptmeplus.enterprise.dao;
import com.adoptmeplus.enterprise.dto.Dog;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface IDogRetrofitDAO {
    @GET("/draft-07/schema#")
    Call<List<Dog>> getDogs(@Query("age") String age);
}