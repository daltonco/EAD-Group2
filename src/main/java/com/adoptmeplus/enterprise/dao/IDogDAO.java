package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * The IDogDAO interface defines the contract for data access operations related to Dog entities
 * in the AdoptMePlus application.
 *
 * Implementing classes should provide methods for saving, retrieving, and querying Dog records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
public interface IDogDAO {

    /**
     * Retrieves a list of all Dog records
     *
     * @return A List of Dog objects representing all available dogs
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    List<Dog> findAll();

    /**
     * Saves a Dog record.
     *
     * @param dog The Dog object to be saved.
     * @return The saved Dog object.
     * @throws Exception if there is an issue with the data access operation.
     */
    Dog save(Dog dog) throws Exception;

    /**
     * Deletes a Dog record.
     *
     * @param dog The Dog object to be deleted.
     * @return The deleted Dog object.
     * @throws Exception if there is an issue with the data access operation.
     */
    void delete(Dog dog) throws Exception;

    /**
     * Retrieves a Dog record by its unique identifier.
     *
     * @param dogId The unique identifier of the Dog to be fetched.
     * @return The Dog object with the specified dogId, or null if not found.
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    Dog fetchDog(int dogId) throws IOException;

    /**
     * Retrieves a list of Dog records based on a specified breed.
     *
     * @param breed The breed of dogs to retrieve.
     * @return A List of Dog objects representing all available dogs of the specified breed.
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    List<Dog> fetchByBreed(String breed) throws IOException;

    List<Dog> findAutocompleteByBreed(String breed);
}
