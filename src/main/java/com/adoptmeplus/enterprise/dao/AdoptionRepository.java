package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

@Profile("test")
public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
    List<Adoption> findAll();

    void delete(Adoption adoption);

    Adoption save(Adoption adoption);



}