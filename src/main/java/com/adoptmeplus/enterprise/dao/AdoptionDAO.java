package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The AdoptionDAO class is responsible for managing and interacting with the database or data source
 * for the Adoption entities in the AdoptMePlus application.
 * This class implements the IAdoptionDAO interface and provides methods for saving and retrieving Adoption records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
@Profile("dev")
public class AdoptionDAO implements IAdoptionDAO{

    @Autowired
    private AdoptionRepository adoptionRepository;

    /**
     * Saves an Adoption record to the data source.
     *
     * @param adoption The Adoption object to be saved.
     * @return The saved Adoption object.
     */
    @Override
    public Adoption save (Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    /**
     * Retrieves a list of all Adoption records from the data source.
     *
     * @return A List of Adoption objects representing all available Adoption records.
     */
    @Override
    public List<Adoption> findAll(){
        return adoptionRepository.findAll();
    }

    /**
     * Deletes an adoption record from the data source.
     *
     * @param adoption The Adoption object to be deleted.
     */

    @Override
    public void delete(Adoption adoption){
        adoptionRepository.delete(adoption);
    }

    /**
     * Fetches a Adoption by its unique identifier.
     *
     * @param adoptionId The unique identifier of the adoption to be fetched.
     * @return The adoption object with the specified adoptionId, or null if not found.
     */
    @Override
    public Adoption fetchAdoption(int adoptionId) {
        return adoptionRepository.findById(adoptionId).get();
    }

    /**
     * Retrieves a Adoption record by its unique identifier.
     *
     * @param adoptionId The unique identifier of the adoption to be fetched.
     * @return The Adoption object with the specified adoptionId, or null if not found.
     */
    @Override
    public Adoption findById(int adoptionId) {
        return adoptionRepository.findById(adoptionId).get();
    }
}
