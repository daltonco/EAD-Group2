package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The CustomerDAO class is responsible for managing and interacting with the data sources
 * for Customer entities in the AdoptMePlus application.
 *
 * This class implements the ICustomerDAO interface and provides methods for saving and retrieving Customer records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository("customerDAO")
@Profile("test")
public class CustomerDAOStub implements ICustomerDAO {

    List<Customer> Customers = new ArrayList<>();
    /**
     * Fetches a list of all Customer records.
     *
     * @return A List of Customer objects representing all available customers of the specified breed.
     */
    @Override
    public List<Customer> findAll(){

        return Customers;
    }

    /**
     * Saves a Customer record to the data source.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     */
    @Override
    public Customer save (Customer customer) {

        Customers.add(customer);
        return customer;
    }

    /**
     * Deletes a Customer record from the data source.
     *
     * @param customer The Customer object to be deleted.
     */

    @Override
    public void delete(Customer customer){
        Customers.remove(customer);
    }

    /**
     * Fetches a Customer by its unique identifier.
     *
     * @param customerId The unique identifier of the Customer to be fetched.
     * @return The Customer object with the specified customerId, or null if not found.
     * @throws IOException if there's an issue with the network communication.
     */
    public Customer fetchCustomer(int customerId) throws IOException {
        return Customers.get(customerId);
    }

    @Override
    public List<Customer> findAutocompleteByEmail(String email) {
        return null;
    }
}
