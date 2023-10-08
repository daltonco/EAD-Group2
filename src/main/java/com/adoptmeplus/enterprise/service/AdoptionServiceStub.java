package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionServiceStub implements IAdoptionService{

    public AdoptionServiceStub(){}
    @Override
    public Adoption save(Adoption adoption) {
        return null;
    }

    @Override
    public List<Dog> fetchAllAdoptions() {
        return null;
    }
}
