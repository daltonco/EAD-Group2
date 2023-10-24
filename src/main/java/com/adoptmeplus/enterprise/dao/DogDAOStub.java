package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("test")
public class DogDAOStub implements IDogDAO {
    Map<Integer, Dog> allDogs = new HashMap<>();
    @Override
    public Dog save(Dog dog) throws Exception {
        allDogs.put(dog.getDogId(), dog);
        return dog;

    }

    @Override
    public List<Dog> fetchAll() throws IOException {
        return new ArrayList<>(allDogs.values());
    }

    @Override
    public Dog fetchDog(int dogId) throws IOException {
        return allDogs.get(dogId);
    }
}
