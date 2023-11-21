package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("!test")
public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
    List<Adoption> findAll();

    void delete(Adoption adoption);

    Adoption save(Adoption adoption);



}