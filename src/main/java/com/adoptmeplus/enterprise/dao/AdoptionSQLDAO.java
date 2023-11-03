package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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

    /**
     * Deletes an Adoption record from the SQL database.
     *
     * @param adoption The Adoption object to be deleted.
     * @return The deleted Adoption object.
     * @throws Exception if there is an issue with the database operation.
     */
    @Override
    public void delete(Adoption adoption){
        adoptionRepository.delete(adoption);
    }

    /**
     * Fetches an adoption record by its unique identifier from the SQL database.
     *
     * @param adoptionId The unique identifier of the Dog to be fetched.
     * @return The Adoption object with the specified adoptionId, or null if not found.
     */
    @Override
    public Adoption fetchAdoption(int adoptionId) throws IOException {
        return adoptionRepository.findById(adoptionId).get();
    }
}
