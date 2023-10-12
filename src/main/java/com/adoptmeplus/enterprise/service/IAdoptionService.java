package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;

import java.io.IOException;
import java.util.List;

public interface IAdoptionService {

    Adoption save(Adoption adoption);

    List<Adoption> fetchAll();
    List<Dog> fetchDogs(String age) throws IOException;
}
