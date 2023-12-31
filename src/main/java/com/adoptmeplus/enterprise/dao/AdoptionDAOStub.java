package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The AdoptionDAO STUB is a placeholder for managing and interacting with the database or data source
 * for the Adoption entities in the AdoptMePlus application.
 * This implements the IAdoptionDAO interface and provides methods for saving and retrieving Adoption records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository("adoptionDAO")
@Profile("test")
public class AdoptionDAOStub implements IAdoptionDAO{

    /**
     * Saves an Adoption record to the data source.
     *
     * @param adoption The Adoption object to be saved.
     * @return The saved Adoption object.
     */
    @Override
    public Adoption save (Adoption adoption) {
        return null;
    }

    /**
     * Retrieves a list of all Adoption records from the data source.
     *
     * @return A List of Adoption objects representing all available Adoption records.
     */
    @Override
    public List<Adoption> findAll(){
        return null;
    }

    /**
     * Deletes an adoption record from the data source.
     *
     * @param adoption The Adoption object to be deleted.
     */

    @Override
    public void delete(Adoption adoption){}

    /**
     * Fetches a Adoption by its unique identifier.
     *
     * @param adoptionId The unique identifier of the adoption to be fetched.
     * @return The adoption object with the specified adoptionId, or null if not found.
     */
    @Override
    public Adoption fetchAdoption(int adoptionId) {
        return null;
    }
}
