package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Customer;

import java.io.IOException;
import java.util.List;

/**
 * The `ICustomerService` interface defines the contract for managing customer-related operations in the AdoptMePlus enterprise system.
 * This interface outlines the methods for saving and fetching customer records, as well as fetching a specific customer by its unique identifier.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public interface ICustomerService {

    /**
     * Saves a customer record to the underlying data source.
     *
     * @param customer The customer record to be saved.
     * @return The saved customer record.
     */
    Customer save(Customer customer) throws Exception;

    /**
     * Retrieves a list of all customer records
     *
     * @return A list of customer records matching the given breed.
     */
    List<Customer> findAll() throws IOException;

    List<Customer> findAutocompleteByEmail(String term) throws IOException;

    /**
     * Retrieves a specific customer record by its unique identifier.
     *
     * @param customerId The unique identifier of the customer to fetch.
     * @return The customer record with the specified identifier.
     */
    Customer fetchCustomer(int customerId) throws IOException;
    /**
     * Deletes a customer record from the underlying data source by its unique identifier.
     *
     * @param customer The unique identifier of the customer to be deleted.
     * @throws Exception If an error occurs during the deletion operation.
     */
    void delete(Customer customer) throws Exception;

}
