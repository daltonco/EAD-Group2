package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository("adoptionSQLDAO")
public class AdoptionSQLDAO implements IAdoptionDAO{
    @Autowired
    AdoptionRepository adoptionRepository;


    @Override
    public Adoption save(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    @Override
    public List<Adoption> findAll() {
        Iterable<Adoption> adoptions = adoptionRepository.findAll();
        List<Adoption> allAdoptions = new ArrayList<>();
        for (Adoption adoption: adoptions) {
            allAdoptions.add(adoption);
        }
        return allAdoptions;
    }
}
