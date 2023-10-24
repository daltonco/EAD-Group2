package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Dog;

import java.io.IOException;
import java.util.List;

public interface IDogService {

    Dog save(Dog dog) throws Exception;
    List<Dog> fetchAll() throws IOException;
    Dog fetchDog(int dogId) throws IOException;
}
