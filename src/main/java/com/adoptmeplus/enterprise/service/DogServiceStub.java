package com.adoptmeplus.enterprise.service;
import com.adoptmeplus.enterprise.dao.IDogDAO;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;


/**
 * The `DogServiceStub` class provides a stub implementation of the `IDogService` interface
 * for managing dog-related operations in the AdoptMePlus enterprise system.
 *
 * This class is responsible for saving and fetching dog records using an underlying `IDogDAO`
 * data access object. It is marked as a Spring service to enable dependency injection.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Service
public class DogServiceStub implements IDogService {

    private final IDogDAO dogDAO;

    /**
     * Constructs a `DogServiceStub` with the specified `IDogDAO`.
     *
     * @param dogDAO The data access object for managing dog records.
     */
    @Autowired
    public DogServiceStub(IDogDAO dogDAO){
        this.dogDAO = dogDAO;
    }

    /**
     * Saves a dog record to the underlying data source.
     *
     * @param dog The dog record to be saved.
     * @return The saved dog record.
     * @throws Exception If an error occurs during the save operation.
     */
    @Override
    public Dog save(Dog dog) throws Exception {
        return dogDAO.save(dog);
    }

    /**
     * Retrieves a list of dog records based on a specified breed.
     *
     * @param breed The breed of dogs to fetch.
     * @return A list of dog records matching the given breed.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    @Override
    public List<Dog> fetchByBreed(String breed) throws IOException {
        return dogDAO.fetchByBreed(breed);
    }

    /**
     * Retrieves a specific dog record by its unique identifier.
     *
     * @param dogId The unique identifier of the dog to fetch.
     * @return The dog record with the specified identifier.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    @Override
    public Dog fetchDog(int dogId) throws IOException {
        return dogDAO.fetchDog(dogId);
    }
}
