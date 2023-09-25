package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdoptionDAOStub implements IAdoptionDAO{
    @Override
    public Adoption save(Adoption adoption) {
        return null;
    }

    @Override
    public List<Dog> fetchAll() {
        return null;
    }
}
