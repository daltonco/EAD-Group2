package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The DogDAO class is responsible for managing and interacting with the data sources
 * for Dog entities in the AdoptMePlus application.
 * This class implements the IDogDAO interface and provides methods for saving and retrieving Dog records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository("dogDAO")
@Profile("test")
public class DogDAOStub implements IDogDAO {

    /**
     * Fetches a list of all Dog records.
     *
     * @return A List of Dog objects representing all available dogs of the specified breed.
     */
    @Override
    public List<Dog> findAll(){
        return null;
    }

    /**
     * Saves a Dog record to the data source.
     *
     * @param dog The Dog object to be saved.
     * @return The saved Dog object.
     */
    @Override
    public Dog save (Dog dog) {
        return null;
    }

    /**
     * Deletes a Dog record from the data source.
     *
     * @param dog The Dog object to be deleted.
     */

    @Override
    public void delete(Dog dog){}

    /**
     * Fetches a Dog by its unique identifier.
     *
     * @param dogId The unique identifier of the Dog to be fetched.
     * @return The Dog object with the specified dogId, or null if not found.
     */
    public Dog fetchDog(int dogId) {
        return null;
    }

    /**
     * Fetches a list of Dog records based on a specified breed.
     *
     * @param breed The breed of dogs to retrieve.
     * @return A List of Dog objects representing all available dogs of the specified breed.
     */
    @Override
    public List<Dog> findAutocompleteByBreed(String breed) {
        return null;
    }
}
