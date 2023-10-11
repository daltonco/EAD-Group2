package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdoptionDAOStub implements IAdoptionDAO{

    List<Adoption> allAdoptions = new ArrayList<Adoption>();


    @Override
    public Adoption save(Adoption adoption) {
        allAdoptions.add(adoption);
        return adoption;
    }

    @Override
    public List<Adoption> fetchAll() {

        return allAdoptions;
    }
}
