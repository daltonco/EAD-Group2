package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Customer;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.dto.LabelValue;
import com.adoptmeplus.enterprise.service.ICustomerService;
import com.adoptmeplus.enterprise.service.IDogService;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.*;

/**
 * The `AdoptMePlusController` class serves as the controller for managing the AdoptMePlus enterprise system's web application.
 * This class handles requests related to adoptions, dog management, searching, and general pages such as the index, contact, and search pages.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Controller
public class AdoptMePlusAPIController {


    private final IAdoptionService adoptionService;
    private final IDogService dogService;
    private final ICustomerService customerService;

    /**
     * Constructs an `AdoptMePlusController` with the specified services for adoption and dog management.
     *
     * @param adoptionService The service for managing adoption records.
     * @param dogService The service for managing dog-related operations.
     */
    @Autowired
    public AdoptMePlusAPIController(IAdoptionService adoptionService, IDogService dogService, ICustomerService customerService) {
        this.adoptionService = adoptionService;
        this.dogService = dogService;
        this.customerService = customerService;
    }


    /*
    * Dog API controller section
    */
    @GetMapping("api/dogs/all")
    @ResponseBody
    public ResponseEntity<List<Dog>> findAllDogs() {
        List<Dog> allDogs = null;
        try {
            allDogs = dogService.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(allDogs, headers, HttpStatus.OK);
    }

    @GetMapping("api/dogs/{dogId}")
    @ResponseBody
    public ResponseEntity fetchDog(@PathVariable("dogId")int dogId) {
        try {
            Dog foundDog = dogService.fetchDog(dogId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(foundDog, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("api/dogs/update/{dogId}")
    public ResponseEntity updateDog(@PathVariable("dogId") int dogId, @RequestBody Dog updatedDog) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            Dog foundDog = dogService.fetchDog(dogId);

            if (foundDog == null){
                return new ResponseEntity("Dog not found", HttpStatus.NOT_FOUND);
            }

            foundDog.setAge(updatedDog.getAge());
            foundDog.setBreed(updatedDog.getBreed());
            foundDog.setFullName(updatedDog.getFullName());
            foundDog.setDateArrived(updatedDog.getDateArrived());
            foundDog.setLocation(updatedDog.getLocation());
            foundDog.setTags(updatedDog.getTags());

            Dog updated = dogService.save(foundDog);


            return new ResponseEntity(updated, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("api/dogs/delete/{dogId}")
    public ResponseEntity deleteDog(@PathVariable("dogId") int dogId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            Dog existingDog = dogService.fetchDog(dogId);

            if (existingDog == null) {
                return new ResponseEntity("Dog not found", HttpStatus.NOT_FOUND);
            }

            // Delete the Dog
            dogService.delete(existingDog);

            return new ResponseEntity("Dog deleted successfully", headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value="api/dogs/add", consumes="application/json", produces="application/json")
    public ResponseEntity addDog(@RequestBody Dog dog) throws Exception {
        Dog newDog = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


            newDog = dogService.save(dog);


        return new ResponseEntity(newDog, headers, HttpStatus.OK);
    }

    /*
     * Customer API controller section
     */
    @GetMapping("api/customers/all")
    @ResponseBody
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> allCustomers = null;
        try {
            allCustomers = customerService.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(allCustomers, headers, HttpStatus.OK);
    }


    @PutMapping("api/customers/update/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer updatedCustomer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            Customer foundCustomer = customerService.fetchCustomer(customerId);

            if (foundCustomer == null){
                return new ResponseEntity("Customer not found", HttpStatus.NOT_FOUND);
            }

            foundCustomer.setFirstName(updatedCustomer.getFirstName());
            foundCustomer.setLastName(updatedCustomer.getLastName());
            foundCustomer.setEmail(updatedCustomer.getEmail());
            foundCustomer.setAddress(updatedCustomer.getAddress());

            Customer updated = customerService.save(foundCustomer);


            return new ResponseEntity(updated, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("api/customers/delete/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") int customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            Customer existingCustomer = customerService.fetchCustomer(customerId);

            if (existingCustomer == null) {
                return new ResponseEntity("Customer not found", HttpStatus.NOT_FOUND);
            }

            // Delete the Customer
            customerService.delete(existingCustomer);

            return new ResponseEntity("Customer deleted successfully", headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value="api/customers/add", consumes="application/json", produces="application/json")
    public ResponseEntity addCustomer(@RequestBody Customer customer) throws Exception {
        Customer newCustomer = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

            newCustomer = customerService.save(customer);


        return new ResponseEntity(newCustomer, headers, HttpStatus.OK);
    }

}
