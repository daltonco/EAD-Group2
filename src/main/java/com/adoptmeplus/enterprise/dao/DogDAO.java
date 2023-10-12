package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;

@Repository
public class DogDAO implements IDogDAO{

    @Override
    public List<Dog> fetchDogs(String age) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IDogRetrofitDAO dogRetrofitDAO = retrofitInstance.create(IDogRetrofitDAO.class);
        Call<List<Dog>> allDogs = dogRetrofitDAO.getDogs(age);
        Response<List<Dog>> execute = allDogs.execute();
        List<Dog> dogs = execute.body();
        return dogs;
    }
}
