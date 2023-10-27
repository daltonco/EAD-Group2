package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import retrofit2.*;
import java.io.IOException;
import java.util.List;
/**
 * The DogDAO class is responsible for managing and interacting with the data sources
 * for Dog entities in the AdoptMePlus application.
 *
 * This class implements the IDogDAO interface and provides methods for saving and retrieving Dog records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
public class DogDAO implements IDogDAO {

    @Autowired
    private DogRepository dogRepository;

    /**
     * Saves a Dog record to the data source.
     *
     * @param dog The Dog object to be saved.
     * @return The saved Dog object.
     */
    @Override
    public Dog save (Dog dog) {
        
        return dogRepository.save(dog);
    }


    /**
     * Fetches a Dog by its unique identifier.
     *
     * @param dogId The unique identifier of the Dog to be fetched.
     * @return The Dog object with the specified dogId, or null if not found.
     * @throws IOException if there's an issue with the network communication.
     */
    public Dog fetchDog(int dogId) throws IOException {
        Dog dog = dogRepository.findById(dogId).get();
        return dog;

    }

    /**
     * Fetches a list of Dog records based on a specified breed.
     *
     * @param breed The breed of dogs to retrieve.
     * @return A List of Dog objects representing all available dogs of the specified breed.
     */
    @Override
    public List<Dog> fetchByBreed(String breed) { return null; }
}
