package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * The CustomerDAO STUB is a placeholder for managing and interacting with the data sources
 * for Customer entities in the AdoptMePlus application.
 * This implements the ICustomerDAO interface and provides methods for saving and retrieving Customer records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */

@Repository("customerDAO")
@Profile("test")
public class CustomerDAOStub implements ICustomerDAO {

    /**
     * Fetches a list of all Customer records.
     *
     * @return A List of Customer objects representing all available customers of the specified breed.
     */
    @Override
    public List<Customer> findAll(){
        return null;
    }

    /**
     * Saves a Customer record to the data source.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     */
    @Override
    public Customer save (Customer customer) {
        return null;
    }

    /**
     * Deletes a Customer record from the data source.
     *
     * @param customer The Customer object to be deleted.
     */

    @Override
    public void delete(Customer customer){}

    /**
     * Fetches a Customer by its unique identifier.
     *
     * @param customerId The unique identifier of the Customer to be fetched.
     * @return The Customer object with the specified customerId, or null if not found.
     */
    public Customer fetchCustomer(int customerId) {
        return null;

    }

    /**
     * Fetches a list of Customer records based on a specified e-mail.
     *
     * @param email The e-mail of Customers to retrieve.
     * @return A List of Customer objects representing all available Customers with the specified e-mail.
     */
    @Override
    public List<Customer> findAutocompleteByEmail(String email) {
        return null;
    }
}
