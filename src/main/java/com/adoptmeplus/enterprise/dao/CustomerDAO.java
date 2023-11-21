package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * The CustomerDAO class is responsible for managing and interacting with the data sources
 * for Customer entities in the AdoptMePlus application.
 * This class implements the ICustomerDAO interface and provides methods for saving and retrieving Customer records.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Repository
@Profile("dev")
public class CustomerDAO implements ICustomerDAO {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Fetches a list of all Customer records.
     *
     * @return A List of Customer objects representing all available customers of the specified breed.
     */
    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    /**
     * Saves a Customer record to the data source.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     */
    @Override
    public Customer save (Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Deletes a Customer record from the data source.
     *
     * @param customer The Customer object to be deleted.
     *
     */

    @Override
    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

    /**
     * Fetches a Customer by its unique identifier.
     *
     * @param customerId The unique identifier of the Customer to be fetched.
     * @return The Customer object with the specified customerId, or null if not found.
     *
     */
    public Customer fetchCustomer(int customerId) {
        return customerRepository.findById(customerId).get();

    }

    /**
     * Fetches a list of Customer records based on a specified email.
     *
     * @param email The email of customers to retrieve.
     * @return A List of Customer objects representing all available customers of the specified email.
     */
    @Override
    public Customer findByEmail(String email) {

        return customerRepository.findByEmail(email);
    }
}
