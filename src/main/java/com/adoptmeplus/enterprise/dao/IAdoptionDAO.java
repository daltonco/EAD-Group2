package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;

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
}
