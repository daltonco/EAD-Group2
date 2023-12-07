package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Dog;

import java.io.IOException;
import java.util.List;

/**
 * The `IDogService` interface defines the contract for managing dog-related operations in the AdoptMePlus enterprise system.
 * This interface outlines the methods for saving and fetching dog records, as well as fetching a specific dog by its unique identifier.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public interface IDogService {

    /**
     * Saves a dog record to the underlying data source.
     *
     * @param dog The dog record to be saved.
     * @return The saved dog record.
     * @throws Exception If an error occurs during the save operation.
     */
    Dog save(Dog dog) throws Exception;

    /**
     * Retrieves a list of all dog records
     *
     * @return A list of dog records matching the given breed.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    List<Dog> findAll() throws IOException;

    /**
     * Retrieves a list of dog records based on a specified breed.
     *
     * @param breed The breed of dogs to fetch.
     * @return A list of dog records matching the given breed.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    List<Dog> findAutocompleteByBreed(String breed) throws IOException;

    /**
     * Retrieves a specific dog record by its unique identifier.
     *
     * @param dogId The unique identifier of the dog to fetch.
     * @return The dog record with the specified identifier.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    Dog fetchDog(int dogId) throws IOException;
    /**
     * Deletes a dog record from the underlying data source by its unique identifier.
     *
     * @param dog The unique identifier of the dog to be deleted.
     * @throws Exception If an error occurs during the deletion operation.
     */
    void delete(Dog dog) throws Exception;
}
