package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("default")
public class DogSQLDAO implements IDogDAO{

    @Autowired
    DogRepository dogRepository;

    @Override
    public Dog save (Dog dog) throws Exception {
        return dogRepository.save(dog);
    }

    @Override
    public List<Dog> fetchAll() {
        List<Dog> allDogs = new ArrayList<>();
        Iterable<Dog> dogs = dogRepository.findAll();
        for (Dog dog: dogs) {
            allDogs.add(dog);
        }
        return allDogs;
    }

    @Override
    public Dog fetchDog(int dogId) {
        return dogRepository.findById(dogId).get();
    }
}
