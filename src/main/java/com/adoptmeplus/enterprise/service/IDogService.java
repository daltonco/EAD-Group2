package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Dog;

import java.io.IOException;
import java.util.List;

public interface IDogService {

    Dog save(Dog dog);
    List<Dog> fetchAll();
    List<Dog> fetchDog(String age) throws IOException;
}
