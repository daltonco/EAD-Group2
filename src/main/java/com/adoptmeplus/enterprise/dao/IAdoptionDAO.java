package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * The IAdoptionDAO interface defines the contract for data access operations related to Adoption entities
 * in the AdoptMePlus application.
 *
 * Implementing classes should provide methods for saving and retrieving Adoption records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
public interface IAdoptionDAO {

    /**
     * Saves an Adoption record.
     *
     * @param adoption The Adoption object to be saved.
     * @return The saved Adoption object.
     */
    Adoption save(Adoption adoption);

    /**
     * Retrieves a list of all Adoption records.
     *
     * @return A List of Adoption objects representing all available Adoption records.
     */
    List<Adoption> findAll();

    /**
     * Deletes an Adoption record from the SQL database.
     *
     * @param adoption The Adoption object to be deleted.
     * @return The deleted Adoption object.
     * @throws Exception if there is an issue with the database operation.
     */
    void delete(Adoption adoption) throws Exception;


    /**
     * Retrieves a Adoption record by its unique identifier.
     *
     * @param adoptionId The unique identifier of the adoption to be fetched.
     * @return The Adoption object with the specified adoptionId, or null if not found.
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    Adoption fetchAdoption(int adoptionId) throws IOException;
}
