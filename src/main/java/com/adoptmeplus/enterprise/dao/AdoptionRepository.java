package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * The AdoptionRepository interface is responsible for defining the data access methods to interact with the data source
 * for Adoptions in the AdoptMePlus application.
 * It extends the CrudRepository interface, providing basic CRUD (Create, Read, Update, Delete) operations for Adoption entities.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Profile("!test")
public interface AdoptionRepository extends CrudRepository<Adoption, Integer> {
    List<Adoption> findAll();
    void delete(Adoption adoption);
    Adoption save(Adoption adoption);



}