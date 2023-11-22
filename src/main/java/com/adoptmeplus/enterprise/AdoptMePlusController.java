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
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The `AdoptMePlusController` class serves as the controller for managing the AdoptMePlus enterprise system's web application.
 * This class handles requests related to adoptions, dog management, searching, and general pages such as the index, contact, and search pages.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Controller
public class AdoptMePlusController {


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
    public AdoptMePlusController(IAdoptionService adoptionService, IDogService dogService, ICustomerService customerService) {
        this.adoptionService = adoptionService;
        this.dogService = dogService;
        this.customerService = customerService;
    }

    /*

    Everything under this section is for page mapping.

     */
    @RequestMapping("/")
    public String index() { return "index"; }

    @RequestMapping("/dogs")
    public String dogs() { return "dogs"; }

    @RequestMapping("/customers")
    public String customers() { return "customers"; }

    @RequestMapping("/adoptions")
    public String adoptions(){ return "adoptions"; }

    @RequestMapping("/dogs/create")
    public String createdog() { return "createdog"; }

    @RequestMapping("/customers/create")
    public String createcustomer() { return "createcustomer"; }

    @RequestMapping("/adoptions/create")
    public String createadoption() { return "createadoption"; }

    @RequestMapping("/dogs/edit")
    public String editdogs() { return "editdogs"; }

    @RequestMapping("/customers/edit")
    public String editcustomers() { return "editcustomers"; }

    @RequestMapping("/adoptions/selectcustomer")
    public String selectcustomer() { return "selectcustomer"; }

    @RequestMapping("/dogs/update")
    public String updatedogs() { return "updatedogs"; }

    @RequestMapping("/customers/update")
    public String updatecustomers() { return "updatecustomers"; }

    @RequestMapping("/adoptions/edit")
    public String editadoptions() { return "editadoptions"; }

    @RequestMapping("/adoptions/update")
    public String updateadoptions() { return "updateadoptions"; }

    @RequestMapping("/adoptions/modify")
    public String modifyadoption() { return "modifyadoption"; }

    /*

    Everything under this section is for REST services.

     */

    /**
     * Handles a GET request to fetch a list of all dogs.
     *
     * @return A ResponseEntity containing a list of all dogs with an HTTP status of 200 (OK).
     */
    @GetMapping("/dogs/all")
    @ResponseBody
    public ResponseEntity<List<Dog>> findAllDogs() {
        List<Dog> allDogs;
        try {
            allDogs = dogService.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(allDogs, headers, HttpStatus.OK);
    }

    /**
     * Handles a POST request to add a new dog to the system.
     * This method receives a JSON representation of a dog object in the request body and attempts to save it using the `dogService`.
     * If the dog is successfully added, it returns a response containing the newly created dog with an HTTP status of 200 (OK).
     * If an error occurs during the addition process, it returns an error response with an HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param dog The JSON representation of the dog to be added.
     * @return A ResponseEntity containing either the newly created dog or an error response.
     */
    @PostMapping(value="/dogs/add", consumes="application/json", produces="application/json")
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog) {
        Dog newDog;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            newDog = dogService.save(dog);
        } catch (Exception e) {

            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newDog, headers, HttpStatus.OK);
    }

    /**
     * Updates an existing Dog resource with the provided information.
     * This method handles a PUT request to update a Dog by its unique identifier. It retrieves the existing Dog from the database,
     * and if the Dog is found, it updates the specified fields with the new values provided in the request body. Only the fields
     * with updated values are modified, and the others remain unchanged. If the Dog is not found, a 404 Not Found response is returned.
     * If an error occurs during the update, a 500 Internal Server Error response is returned.
     *
     * @param dogId The unique identifier of the Dog to be updated.
     * @param updatedDog The JSON representation of the Dog with updated fields.
     * @return A ResponseEntity containing either the updated Dog resource or an error response.
     */
    @PutMapping("/dogs/update/{dogId}")
    public ResponseEntity<Dog> updateDog(@PathVariable("dogId") int dogId, @RequestBody Dog updatedDog) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            Dog foundDog = dogService.fetchDog(dogId);

            if (foundDog == null){
                return new ResponseEntity<>(updatedDog, HttpStatus.NOT_FOUND);
            }

            foundDog.setAge(updatedDog.getAge());
            foundDog.setBreed(updatedDog.getBreed());
            foundDog.setFullName(updatedDog.getFullName());
            foundDog.setDateArrived(updatedDog.getDateArrived());
            foundDog.setLocation(updatedDog.getLocation());
            foundDog.setTags(updatedDog.getTags());

            Dog updated = dogService.save(foundDog);


            return new ResponseEntity<>(updated, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles a GET request to fetch information about a specific dog by its unique identifier.
     * This method takes the `dogId` as a path variable and attempts to retrieve the corresponding dog's information
     * using the `dogService`. If the dog is found, it returns a response containing the dog's information with an
     * HTTP status of 200 (OK). If an error occurs during the fetch operation, it returns an error response with an
     * HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param dogId The unique identifier of the dog to fetch.
     * @return A ResponseEntity containing either the fetched dog's information or an error response.
     */
    @GetMapping("/dogs/{dogId}")
    @ResponseBody
    public ResponseEntity<Dog> fetchDog(@PathVariable("dogId")int dogId) {
        try {
            Dog foundDog = dogService.fetchDog(dogId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(foundDog, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a Dog resource by its unique identifier.
     * This method handles a DELETE request to delete a Dog by its unique identifier. It attempts to retrieve the existing Dog from
     * the database and, if found, deletes the Dog. If the Dog is not found, a 404 Not Found response is returned. If an error occurs
     * during the deletion, a 500 Internal Server Error response is returned.
     *
     * @param dogId The unique identifier of the Dog to be deleted.
     * @return A ResponseEntity indicating the success of the deletion or an error response.
     */
    @DeleteMapping("/dogs/delete/{dogId}")
    public ResponseEntity<String> deleteDog(@PathVariable("dogId") int dogId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            Dog existingDog = dogService.fetchDog(dogId);

            if (existingDog == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Delete the Dog
            dogService.delete(existingDog);

            return new ResponseEntity<>("Dog deleted successfully", headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles a POST request to create a new adoption record.
     * This method receives a JSON representation of an adoption object in the request body and attempts to save it using
     * the `adoptionService`. If the adoption record is successfully created, it returns the newly created adoption object
     * with an HTTP status of 200 (OK). If an error occurs during the creation process, it returns an error response.
     *
     * @param adoption The JSON representation of the adoption object to be created.
     * @return A ResponseEntity containing either the newly created adoption object or an error response.
     */
    @PostMapping(value="/adoptions/add", consumes="application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Adoption> createAdoption(@RequestBody Adoption adoption, @RequestParam("customerEmail") String customerEmail){
        Adoption newAdoption;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            Customer customer = customerService.findByEmail(customerEmail);
            if (customer != null) {

                adoption.setCustomer(customer);

                newAdoption = adoptionService.save(adoption);

                return new ResponseEntity<>(newAdoption, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * Updates an existing Adoption resource with the provided information.
     * This method handles a PUT request to update an Adoption by its unique identifier. It retrieves the existing Adoption from the database,
     * and if an Adoption is found, it updates the specified fields with the new values provided in the request body. Only the fields
     * with updated values are modified, and the others remain unchanged. If an Adoption is not found, a 404 Not Found response is returned.
     * If an error occurs during the update, a 500 Internal Server Error response is returned.
     *
     * @param adoptionId The unique identifier of the Adoption to be updated.
     * @param updatedAdoption The JSON representation of an Adoption with updated fields.
     * @return A ResponseEntity containing either the updated Adoption resource or an error response.
     */
    @PutMapping("/adoption/update/{adoptionId}")
    public ResponseEntity<Adoption> updateAdoption(@PathVariable("adoptionId") int adoptionId, @RequestBody Adoption updatedAdoption) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            Adoption foundAdoption = adoptionService.fetchAdoption(adoptionId);

            if (foundAdoption == null){
                return new ResponseEntity<>(updatedAdoption, HttpStatus.NOT_FOUND);
            }

            foundAdoption.setDog(updatedAdoption.getDog());
            foundAdoption.setCustomer(updatedAdoption.getCustomer());

            Adoption updated = adoptionService.save(foundAdoption);


            return new ResponseEntity<>(updated, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles a GET request to fetch a list of all adoptions.
     *
     * @return A ResponseEntity containing a list of all adoptions with an HTTP status of 200 (OK).
     */
    @GetMapping("/adoptions/all")
    @ResponseBody
    public ResponseEntity<List<Adoption>> findAllAdoptions() {
        List<Adoption> allAdoptions;

        allAdoptions = adoptionService.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(allAdoptions, headers, HttpStatus.OK);
    }

    @GetMapping("/adoptions/{id}")
    public ResponseEntity<Adoption> fetchAdoptionsById(@PathVariable("id") int id){
        try {
            Adoption adoptions = adoptionService.fetchAdoption(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(adoptions, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/adoptions/delete/{id}")
    public ResponseEntity<String> deleteAdoption(@PathVariable("id") int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            Adoption existingAdoption = adoptionService.fetchAdoption(id);

            if (existingAdoption == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Delete adoption
            adoptionService.delete(existingAdoption);

            return new ResponseEntity<>("Adoption deleted successfully", headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles a POST request to add a new customer to the system.
     * This method receives a JSON representation of a customer object in the request body and attempts to save it using the `customerService`.
     * If the customer is successfully added, it returns a response containing the newly created customer with an HTTP status of 200 (OK).
     * If an error occurs during the addition process, it returns an error response with an HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param customer The JSON representation of the customer to be added.
     * @return A ResponseEntity containing either the newly created customer or an error response.
     */
    @PostMapping(value="/customer/add", consumes="application/json", produces="application/json")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {

            Customer existingCustomer = customerService.findByEmail(customer.getEmail());

            if (existingCustomer != null) {
                return new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
            }

            newCustomer = customerService.save(customer);

        } catch (Exception e) {

            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newCustomer, headers, HttpStatus.OK);
    }

    /**
     * Handles a GET request to fetch a list of all customers.
     *
     * @return A ResponseEntity containing a list of all customers with an HTTP status of 200 (OK).
     */
    @GetMapping("/customers/all")
    @ResponseBody
    public ResponseEntity<List<Customer>> findAllCustomers() throws IOException {
        List<Customer> allCustomers;

        allCustomers = customerService.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(allCustomers, headers, HttpStatus.OK);
    }

    /**
     * Deletes a Customer resource by its unique identifier.
     * This method handles a DELETE request to delete a Customer by its unique identifier. It attempts to retrieve the existing Customer from
     * the database and, if found, deletes the Customer. If the Customer is not found, a 404 Not Found response is returned. If an error occurs
     * during the deletion, a 500 Internal Server Error response is returned.
     *
     * @param customerId The unique identifier of the Customer to be deleted.
     * @return A ResponseEntity indicating the success of the deletion or an error response.
     */
    @DeleteMapping("/customers/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") int customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            Customer existingCustomer = customerService.fetchCustomer(customerId);

            if (existingCustomer == null) {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            }

            customerService.delete(existingCustomer);

            return new ResponseEntity<>("Customer deleted successfully", headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("dogs/dogNamesAutocomplete")
    @ResponseBody
    public List<LabelValue> dogNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) throws IOException {
        List<LabelValue> allDogBreeds = new ArrayList<>();
        try {
        List<Dog> dogs = dogService.fetchByBreed(term);
        for (Dog dog: dogs) {
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(dog.getBreed());
            labelValue.setValue(dog.getDogId());
            allDogBreeds.add(labelValue);
            allDogBreeds.sort(Comparator.comparing(LabelValue::getLabel));
        }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
            }
        return allDogBreeds;
    }
    /**
     * Updates an existing Customer resource with the provided information.
     * This method handles a PUT request to update a Customer by its unique identifier. It retrieves the existing Customer from the database,
     * and if the Customer is found, it updates the specified fields with the new values provided in the request body. Only the fields
     * with updated values are modified, and the others remain unchanged. If the Customer is not found, a 404 Not Found response is returned.
     * If an error occurs during the update, a 500 Internal Server Error response is returned.
     *
     * @param customerId The unique identifier of the Customer to be updated.
     * @param updatedCustomer The JSON representation of the Customer with updated fields.
     * @return A ResponseEntity containing either the updated Customer resource or an error response.
     */
    @PutMapping("/customers/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer updatedCustomer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            Customer foundCustomer = customerService.fetchCustomer(customerId);

            if (foundCustomer == null){
                return new ResponseEntity<>(updatedCustomer, HttpStatus.NOT_FOUND);
            }

            if(!updatedCustomer.getEmail().equals(foundCustomer.getEmail())) {
                Customer existingCustomer = customerService.findByEmail(updatedCustomer.getEmail());

                if (existingCustomer != null) {
                    return new ResponseEntity<>(updatedCustomer, HttpStatus.BAD_REQUEST);
                }
            }

            foundCustomer.setFirstName(updatedCustomer.getFirstName());
            foundCustomer.setLastName(updatedCustomer.getLastName());
            foundCustomer.setEmail(updatedCustomer.getEmail());
            foundCustomer.setAddress(updatedCustomer.getAddress());


            Customer updated = customerService.save(foundCustomer);


            return new ResponseEntity<>(updated, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}