package com.adoptmeplus.enterprise.service;
import com.adoptmeplus.enterprise.dao.CustomerRepository;
import com.adoptmeplus.enterprise.dao.ICustomerDAO;
import com.adoptmeplus.enterprise.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;


/**
 * The `CustomerService` class provides the implementation of the `ICustomerService` interface
 * for managing customer-related operations in the AdoptMePlus enterprise system.
 *
 * This class is responsible for saving and fetching customer records using an underlying `ICustomerDAO`
 * data access object. It is marked as a Spring service to enable dependency injection.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final ICustomerDAO customerDAO;

    /**
     * Constructs a `CustomerService` with the specified `ICustomerDAO`.
     *
     * @param customerDAO The data access object for managing customer records.
     */
    @Autowired
    public CustomerService(ICustomerDAO customerDAO, CustomerRepository customerRepository){

        this.customerDAO = customerDAO;
        this.customerRepository = customerRepository;
    }

    /**
     * Saves a customer record to the underlying data source.
     *
     * @param customer The customer record to be saved.
     * @return The saved customer record.
     * @throws Exception If an error occurs during the save operation.
     */
    @Override
    public Customer save(Customer customer) throws Exception {
        return customerDAO.save(customer);
    }

    @Override
    public List<Customer> findAll() throws IOException {
        return customerDAO.findAll();
    }

    /**
     * Retrieves a customer record based on a specified email.
     *
     * @param email The email of a customer to fetch.
     * @return A customer record matching the given email.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    @Override
    public Customer findByEmail(String email) throws IOException {
        return customerDAO.findByEmail(email);
    }

    /**
     * Retrieves a specific customer record by its unique identifier.
     *
     * @param customerId The unique identifier of the customer to fetch.
     * @return The customer record with the specified identifier.
     * @throws IOException If an I/O error occurs during the fetch operation.
     */
    @Override
    public Customer fetchCustomer(int customerId) throws IOException {
        return customerDAO.fetchCustomer(customerId);
    }

    @Override
    public void delete(Customer customer) throws Exception {
        if (customer != null) {
            customerRepository.delete(customer);
        } else {
            throw new Exception("Customer not found");
        }
    }
}
