package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;

import java.util.List;

public interface IAdoptionService {


    Adoption save(Adoption adoption);
    List<Dog> fetchAll();
}
