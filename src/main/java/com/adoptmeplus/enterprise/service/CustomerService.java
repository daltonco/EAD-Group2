package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dao.ICustomerDAO;
import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;


/**
 * The `CustomerService` class provides the implementation of the `ICustomerService` interface
 * for managing customer-related operations in the AdoptMePlus enterprise system.
 * This class is responsible for saving and fetching customer records using an underlying `ICustomerDAO`
 * data access object. It is marked as a Spring service to enable dependency injection.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerDAO customerDAO;

    /**
     * Constructs a `CustomerService` with the specified `ICustomerDAO`.
     *
     * @param customerDAO The data access object for managing customer records.
     */
    @Autowired
    public CustomerService(ICustomerDAO customerDAO){

        this.customerDAO = customerDAO;
    }

    /**
     * Saves a customer record to the underlying data source.
     *
     * @param customer The customer record to be saved.
     * @return The saved customer record.
     */
    @Override
    public Customer save(Customer customer) throws Exception {
        return customerDAO.save(customer);
    }

    /**
     * Retrieves a list of all customer records
     *
     * @return A list of customer records matching the given breed.
     */
    @Override
    public List<Customer> findAll() throws IOException {
        return customerDAO.findAll();
    }

    /**
     * Retrieves a specific customer record by its unique identifier.
     *
     * @param customerId The unique identifier of the customer to fetch.
     * @return The customer record with the specified identifier.
     */
    @Override
    public Customer fetchCustomer(int customerId) throws IOException {
        return customerDAO.fetchCustomer(customerId);
    }

    /**
     * Deletes a customer record from the underlying data source by its unique identifier.
     *
     * @param customer The unique identifier of the customer to be deleted.
     * @throws Exception If an error occurs during the deletion operation.
     */
    @Override
    public void delete(Customer customer) throws Exception {
        if (customer != null) {
            customerDAO.delete(customer);
        } else {
            throw new Exception("Customer not found");
        }
    }

    /**
     * Retrieves a list of Customer records based on a specified e-mail.
     *
     * @param email The email of Customers to fetch.
     * @return A list of customer records matching the given e-mail.
     */
    public List<Customer> findAutocompleteByEmail(String email) {
        return customerDAO.findAutocompleteByEmail(email);

    }
}
