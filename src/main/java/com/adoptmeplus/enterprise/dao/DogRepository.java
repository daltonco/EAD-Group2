package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.data.repository.CrudRepository;

/**
 * The DogRepository interface is responsible for defining the data access methods to interact with the data source
 * for Dog entities in the AdoptMePlus application.
 *
 * It extends the CrudRepository interface, providing basic CRUD (Create, Read, Update, Delete) operations for Dog entities.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public interface DogRepository extends CrudRepository<Dog, Integer> {
}