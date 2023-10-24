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

    public Dog fetchDog(int dogId) throws IOException {
        Retrofit retrofitInstance = RetrofitClientInstance.getRetrofitInstance();
        IDogRetrofitDAO dogRetrofitDAO = retrofitInstance.create(IDogRetrofitDAO.class);
        Call<Dog> gotDog = dogRetrofitDAO.getDog(dogId);
        Response<Dog> execute = gotDog.execute();
        Dog dogs = execute.body();
        return dogs;
    }

    @Override
    public List<Dog> fetchAll(String breed) {
        return null;
    }
}
