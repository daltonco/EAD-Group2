package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import retrofit2.*;
import java.io.IOException;
import java.util.List;

@Repository
public class DogDAO implements IDogDAO {

    @Autowired
    private DogRepository dogRepository;
    @Override
    public Dog save (Dog dog) {
        return dogRepository.save(dog);
    }

    public List<Dog> fetchDog(String age) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IDogRetrofitDAO dogRetrofitDAO = retrofitInstance.create(IDogRetrofitDAO.class);
        Call<List<Dog>> allDogs = dogRetrofitDAO.getDogs(age);
        Response<List<Dog>> execute = allDogs.execute();
        return execute.body();
    }

    @Override
    public List<Dog> fetchAll() {
        return null;
    }
}
