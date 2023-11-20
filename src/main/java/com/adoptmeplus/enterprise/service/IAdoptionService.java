package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Adoption;

import java.io.IOException;
import java.util.List;

/**
 * The `IAdoptionService` interface defines the contract for managing adoption records in the AdoptMePlus enterprise system.
 *
 * This interface outlines the methods for saving and fetching adoption records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public interface IAdoptionService {

    /**
     * Saves an adoption record to the underlying data source.
     *
     * @param adoption The adoption record to be saved.
     * @return The saved adoption record.
     */
    Adoption save(Adoption adoption);

    /**
     * Retrieves a list of all adoption records from the underlying data source.
     *
     * @return A list of adoption records.
     */
    List<Adoption> findAll();

    /**
     * Retrieves a specific adoption record by its unique identifier.
     *
     * @param adoptionId The unique identifier of the adoption to fetch.
     * @return The adoption record with the specified identifier.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    Adoption fetchAdoption(int adoptionId) throws IOException;

    void delete(Adoption adoption) throws IOException, Exception;
}