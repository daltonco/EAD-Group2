package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dao.IAdoptionDAO;
import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionServiceStub implements IAdoptionService{

    @Autowired
    private IAdoptionDAO adoptionDAO;

    public AdoptionServiceStub(){}
    @Override
    public Adoption save(Adoption adoption) {

        return adoptionDAO.save(adoption);
    }



    @Override
    public List<Adoption> fetchAll() {

        return adoptionDAO.fetchAll();
    }
}
