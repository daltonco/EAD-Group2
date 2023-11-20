package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The CustomerRepository interface is responsible for defining the data access methods to interact with the data source
 * for Customer entities in the AdoptMePlus application.
 *
 * It extends the CrudRepository interface, providing basic CRUD (Create, Read, Update, Delete) operations for Customer entities.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Profile("!test")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByEmail(String email);
    void delete(Customer customer);

    Customer save(Customer customer);


    List<Customer> findAll();
}
