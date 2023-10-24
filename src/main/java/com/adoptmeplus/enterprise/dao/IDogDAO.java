package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;

import java.io.IOException;
import java.util.List;

public interface IDogDAO {

    Dog save(Dog dog) throws Exception;
    Dog fetchDog(int dogId) throws IOException;
    List<Dog> fetchAll() throws IOException;
}
