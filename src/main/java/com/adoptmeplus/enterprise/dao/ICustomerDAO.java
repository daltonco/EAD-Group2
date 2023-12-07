package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * The ICustomerDAO interface defines the contract for data access operations related to Customer entities
 * in the AdoptMePlus application.
 * Implementing classes should provide methods for saving, retrieving, and querying Customer records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
public interface ICustomerDAO {

    /**
     * Retrieves a list of all Customer records
     *
     * @return A List of Customer objects representing all available customers
     */
    List<Customer> findAll();

    /**
     * Saves a Customer record.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     */
    Customer save(Customer customer) throws Exception;

    /**
     * Deletes a Customer record.
     *
     * @param customer The Customer object to be deleted.
     */
    void delete(Customer customer) throws Exception;

    /**
     * Retrieves a Customer record by its unique identifier.
     *
     * @param customerId The unique identifier of the Customer to be fetched.
     * @return The Customer object with the specified customerId, or null if not found.
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    Customer fetchCustomer(int customerId) throws IOException;

    /**
     * Retrieves a list of Customer records based on a specified breed.
     *
     * @param email The breed of dogs to retrieve.
     * @return A List of Customer objects representing all available dogs of the specified breed.
     */
    List<Customer> findAutocompleteByEmail(String email);
}
