package com.adoptmeplus.enterprise.service;
import com.adoptmeplus.enterprise.dao.IAdoptionDAO;
import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * The `AdoptionService` class provides the implementation of the `IAdoptionService` interface
 * for managing adoption records in the AdoptMePlus enterprise system.
 *
 * This class is responsible for saving and fetching adoption records using an underlying `IAdoptionDAO`
 * data access object. It is marked as a Spring service to enable dependency injection.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Service
public class AdoptionService implements IAdoptionService{

    private final IAdoptionDAO adoptionDAO;

    /**
     * Constructs an `AdoptionService` with the specified `IAdoptionDAO`.
     *
     * @param adoptionDAO The data access object for managing adoption records.
     */
    @Autowired
    public AdoptionService(IAdoptionDAO adoptionDAO) {
        this.adoptionDAO = adoptionDAO;
    }

    /**
     * Saves an adoption record to the underlying data source.
     *
     * @param adoption The adoption record to be saved.
     * @return The saved adoption record.
     */
    @Override
    public Adoption save(Adoption adoption) {

        return adoptionDAO.save(adoption);
    }

    /**
     * Retrieves a list of all adoption records from the underlying data source.
     *
     * @return A list of adoption records.
     */
    @Override
    public List<Adoption> findAll() {

        return adoptionDAO.findAll();
    }

    /**
     * Retrieves a specific adoption record by its unique identifier.
     *
     * @param adoptionId The unique identifier of the adoption to fetch.
     * @return The adoption record with the specified identifier.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    @Override
    public Adoption fetchAdoption(int adoptionId) throws IOException {
        return adoptionDAO.fetchAdoption(adoptionId);
    }

    @Override
    public void delete(Adoption adoption) throws Exception {
        if (adoption != null) {
            adoptionDAO.delete(adoption);
        } else {
            throw new Exception("Adoption not found");
        }
    }

    @Override
    public Adoption findById(int adoptionId) throws IOException {
        return adoptionDAO.findById(adoptionId);
    }
}