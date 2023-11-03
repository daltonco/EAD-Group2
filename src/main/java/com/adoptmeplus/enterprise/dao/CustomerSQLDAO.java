package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The CustomerSQLDAO class is responsible for managing and interacting with a SQL database
 * for Customer entities in the AdoptMePlus application.
 *
 * This class implements the ICustomerDAO interface and provides methods for saving, retrieving, and querying Customer records from the database.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository("customerSQLDAO")
public class CustomerSQLDAO implements ICustomerDAO{

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Saves a Customer record to the SQL database.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     * @throws Exception if there is an issue with the database operation.
     */
    @Override
    public Customer save (Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    /**
     * Deletes a Customer record from the SQL database.
     *
     * @param customer The Customer object to be deleted.
     * @return The deleted Customer object.
     * @throws Exception if there is an issue with the database operation.
     */
    @Override
    public void delete(Customer customer) throws Exception {
        customerRepository.delete(customer);
    }

    /**
     * Fetches a list of Customer records based on a specified email from the SQL database.
     *
     * @param email The email of customers to retrieve.
     * @return A List of Customer objects representing all available customers of the specified email.
     */
    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }


    /**
     * Fetches a list of all Customer records
     *
     * @return A List of Customer objects representing all available customers of the specified email.
     */
    @Override
    public List<Customer> findAll(){
        List<Customer> allCustomers = new ArrayList<>();
        Iterable<Customer> customers = customerRepository.findAll();
        for (Customer customer: customers) {
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    /**
     * Fetches a Customer record by its unique identifier from the SQL database.
     *
     * @param customerId The unique identifier of the Customer to be fetched.
     * @return The Customer object with the specified customerId, or null if not found.
     */
    @Override
    public Customer fetchCustomer(int customerId) {

        return customerRepository.findById(customerId).get();
    }
}
