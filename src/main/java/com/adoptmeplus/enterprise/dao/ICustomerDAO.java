package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;

import java.io.IOException;
import java.util.List;

/**
 * The ICustomerDAO interface defines the contract for data access operations related to Customer entities
 * in the AdoptMePlus application.
 *
 * Implementing classes should provide methods for saving, retrieving, and querying Customer records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
public interface ICustomerDAO {

    /**
     * Retrieves a list of all Customer records
     *
     * @return A List of Customer objects representing all available customers
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    List<Customer> findAll();

    /**
     * Saves a Customer record.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     * @throws Exception if there is an issue with the data access operation.
     */
    Customer save(Customer customer) throws Exception;

    /**
     * Deletes a Customer record.
     *
     * @param customer The Customer object to be deleted.
     * @return The deleted Customer object.
     * @throws Exception if there is an issue with the data access operation.
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
     * @param email The email of customers to retrieve.
     * @return A List of Customer objects representing all available customers of the specified email.
     * @throws IOException if there's an issue with the data retrieval operation.
     */
    Customer findByEmail(String email) throws IOException;
}
