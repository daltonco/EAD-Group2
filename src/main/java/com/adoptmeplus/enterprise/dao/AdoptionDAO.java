package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * The AdoptionDAO class is responsible for managing and interacting with the database or data source
 * for the Adoption entities in the AdoptMePlus application.
 *
 * This class implements the IAdoptionDAO interface and provides methods for saving and retrieving Adoption records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
public class AdoptionDAO implements IAdoptionDAO{

    /**
     * A list to store all Adoption records in memory.
     */
    List<Adoption> allAdoptions = new ArrayList<>();

    /**
     * Saves an Adoption record to the data source.
     *
     * @param adoption The Adoption object to be saved.
     * @return The saved Adoption object.
     */
    @Override
    public Adoption save(Adoption adoption) {
        allAdoptions.add(adoption);
        return adoption;
    }

    /**
     * Retrieves a list of all Adoption records from the data source.
     *
     * @return A List of Adoption objects representing all available Adoption records.
     */
    @Override
    public List<Adoption> findAll() {

        return allAdoptions;
    }
}
