package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * The DogSQLDAO class is responsible for managing and interacting with a SQL database
 * for Dog entities in the AdoptMePlus application.
 * This class implements the IDogDAO interface and provides methods for saving, retrieving, and querying Dog records from the database.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
@Profile("dev")
public class DogSQLDAO implements IDogDAO{

    final DogRepository dogRepository;

    public DogSQLDAO(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * Saves a Dog record to the SQL database.
     *
     * @param dog The Dog object to be saved.
     * @return The saved Dog object.
     * @throws Exception if there is an issue with the database operation.
     */
    @Override
    public Dog save (Dog dog) throws Exception {
        return dogRepository.save(dog);
    }

    /**
     * Deletes a Dog record from the SQL database.
     *
     * @param dog The Dog object to be deleted.
     * @throws Exception if there is an issue with the database operation.
     */
    @Override
    public void delete(Dog dog) throws Exception {
        dogRepository.delete(dog);
    }

    /**
     * Fetches a list of Dog records based on a specified breed from the SQL database.
     *
     * @param breed The breed of dogs to retrieve.
     * @return A List of Dog objects representing all available dogs of the specified breed.
     */
    @Override
    public List<Dog> findAutocompleteByBreed(String breed) {
        List<Dog> dogs = findAll();
        List<Dog> filteredDogs = new ArrayList<>();
        for (Dog dog : dogs) {
            if (dog.getBreed().toLowerCase().startsWith(breed.toLowerCase())) {
                filteredDogs.add(dog);
            }
        }
        return filteredDogs;
    }


    /**
     * Fetches a list of all Dog records
     *
     * @return A List of Dog objects representing all available dogs of the specified breed.
     */
    @Override
    public List<Dog> findAll(){
        List<Dog> allDogs = new ArrayList<>();
        Iterable<Dog> dogs = dogRepository.findAll();
        for (Dog dog: dogs) {
            allDogs.add(dog);
        }
        return allDogs;
    }

    /**
     * Fetches a Dog record by its unique identifier from the SQL database.
     *
     * @param dogId The unique identifier of the Dog to be fetched.
     * @return The Dog object with the specified dogId, or null if not found.
     */
    @Override
    public Dog fetchDog(int dogId) {

        return dogRepository.findById(dogId).get();
    }
}
