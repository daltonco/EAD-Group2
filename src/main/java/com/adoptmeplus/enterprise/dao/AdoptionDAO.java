package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdoptionDAO implements IAdoptionDAO{

    List<Adoption> allAdoptions = new ArrayList<>();


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
