package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;

import java.io.IOException;
import java.util.List;

public interface IDogDAO {
    List<Dog> fetchDogs(String age) throws IOException;
}
