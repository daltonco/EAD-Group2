package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
    
}