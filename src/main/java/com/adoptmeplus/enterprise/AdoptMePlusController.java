package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Customer;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.dto.LabelValue;
import com.adoptmeplus.enterprise.service.DogService;
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
    public String updatedogs() { return "updatedog"; }

    @RequestMapping("/customers/update")
    public String updatecustomers() { return "updatecustomer"; }

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
    public ResponseEntity<List<Dog>> findAllDogs(@RequestParam(value = "breed", required = false) String breed) {
        List<Dog> filteredDogs;
        try {
            if (breed != null && !breed.isEmpty()) {
                filteredDogs = dogService.fetchByBreed(breed);
            } else {
                filteredDogs = dogService.findAll();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(filteredDogs, headers, HttpStatus.OK);
    }

    @GetMapping("/dogs")
    public String showDogs(Model model) {
        try {
            List<Dog> dogsList = dogService.findAll();
            model.addAttribute("dogsList", dogsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dogs";
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
    @PostMapping(value="/dogs/add")
    public String addDog(@ModelAttribute Dog dog, RedirectAttributes redirectAttributes) {
        try {
            Dog newDog = dogService.save(dog);


            redirectAttributes.addFlashAttribute("successMessage", "Dog added successfully!");
        } catch (Exception e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add dog.");
        }

        return "redirect:/dogs";
    }

    /**
     * Updates an existing Dog resource with the provided information.
     * This method handles a PUT request to update a Dog by its unique identifier. It retrieves the existing Dog from the database,
     * and if the Dog is found, it updates the specified fields with the new values provided in the request body. Only the fields
     * with updated values are modified, and the others remain unchanged. If the Dog is not found, a 404 Not Found response is returned.
     * If an error occurs during the update, a 500 Internal Server Error response is returned.
     *
     * @param dogId The unique identifier of the Dog to be updated.
     * @return A ResponseEntity containing either the updated Dog resource or an error response.
     */
    @PostMapping("/dogs/update/{dogId}")
    public String updateDog(@PathVariable("dogId") int dogId, @ModelAttribute Dog dog) {
        try {

            Dog existingDog = dogService.fetchDog(dogId);


            existingDog.setFullName(dog.getFullName());
            existingDog.setAge(dog.getAge());
            existingDog.setBreed(dog.getBreed());
            existingDog.setLocation(dog.getLocation());
            existingDog.setTags(dog.getTags());


            dogService.save(existingDog);

            return "redirect:/dogs";
        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
    }

    @GetMapping("/updatedog/{dogId}")
    public String updateDogPage(@PathVariable(value = "dogId") int dogId, Model model) throws IOException {

        Dog dog = dogService.fetchDog(dogId);

        model.addAttribute("dog", dog);

        return "updatedog";
    }

    @GetMapping("/deletedog/{dogId}")
    public String deleteDogPage(@PathVariable(value = "dogId") int dogId, Model model, RedirectAttributes redirectAttributes) throws Exception {

        Dog dog = dogService.fetchDog(dogId);

        // Add the retrieved dog information to the model to be used in Thymeleaf
        model.addAttribute("dog", dog);
        if (dog == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "Dog not found");
            return "redirect:/dogs";
        }

        dogService.delete(dog);


        redirectAttributes.addFlashAttribute("successMessage", "Dog deleted successfully");
        return "redirect:/dogs";

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
    @DeleteMapping("api/dogs/delete/{dogId}")
    public ResponseEntity<String> deleteDogAPI(@PathVariable("dogId") int dogId) {
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

    @PostMapping("/dogs/delete/{dogId}")
    public String deleteDog(@PathVariable("dogId") int dogId, @ModelAttribute Dog dog, RedirectAttributes redirectAttributes) {
        try {
            Dog existingDog = dogService.fetchDog(dogId);

            if (existingDog == null) {

                redirectAttributes.addFlashAttribute("errorMessage", "Dog not found");
                return "redirect:/dogs";
            }

            dogService.delete(existingDog);


            redirectAttributes.addFlashAttribute("successMessage", "Dog deleted successfully");
            return "redirect:/dogs";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete dog");
            return "redirect:/dogs";
        }
    }

    @GetMapping("/adoptions")
    public String showAdoptions(
            Model model) {
        try {
            List<Adoption> adoptionsList = adoptionService.findAll();
            model.addAttribute("adoptionsList", adoptionsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adoptions";
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
    @PostMapping(value="/api/adoptions/add", consumes="application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Adoption> createAdoptionAPI(@RequestBody Adoption adoption, @RequestParam("customerEmail") String customerEmail){
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
    @PutMapping("/api/adoption/update/{adoptionId}")
    public ResponseEntity<Adoption> updateAdoptionAPI(@PathVariable("adoptionId") int adoptionId, @RequestBody Adoption updatedAdoption) {
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

    @GetMapping("customers/customerNamesAutocomplete")
    @ResponseBody
    public List<LabelValue> customerNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) throws IOException {
        List<LabelValue> allEmails = new ArrayList<>();
        List<Customer> customers = customerService.findAutocompleteByEmail(term);
        for (Customer customer: customers) {
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(customer.getEmail() + " (" + customer.getLastName() + ", " + customer.getFirstName() + ")");
            labelValue.setValue(customer.getCustomerId());
            allEmails.add(labelValue);
            allEmails.sort(Comparator.comparing(LabelValue::getLabel));
        }
        return allEmails;
    }

    @GetMapping("/customers")
    public String showCustomer(Model model) {
        try {
            List<Customer> customerList = customerService.findAll();
            model.addAttribute("customerList", customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "customers";
    }

    @PostMapping(value="/customers/add")
    public String addCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            Customer newCustomer = customerService.save(customer);


            redirectAttributes.addFlashAttribute("successMessage", "Dog added successfully!");
        } catch (Exception e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add dog.");
        }

        return "redirect:/customers";
    }

    @GetMapping("/updatecustomer/{customerId}")
    public String updateCustomerPage(@PathVariable(value = "customerId") int customerId, Model model) throws IOException {

        Customer customer = customerService.fetchCustomer(customerId);

        model.addAttribute("customer", customer);

        return "updatecustomer";
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
        List<Dog> dogs = dogService.findAutocompleteByBreed(term);
        for (Dog dog: dogs) {
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(dog.getBreed() + " (Name: " + dog.getFullName() + ", Age: " + dog.getAge() + ", Location: " + dog.getLocation() + ")");
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
    @PutMapping("api/customers/update/{customerId}")
    public ResponseEntity<Customer> updateCustomerAPI(@PathVariable("customerId") int customerId, @RequestBody Customer updatedCustomer) {
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

    @PostMapping("/customers/update/{customerId}")
    public String updateCustomer(@PathVariable("customerId") int customerId, @ModelAttribute Customer customer) {
        try {

            Customer existingCustomer = customerService.fetchCustomer(customerId);


            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setAddress(customer.getAddress());


            customerService.save(existingCustomer);

            return "redirect:/customers";
        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
    }

    @GetMapping("/deletecustomer/{customerId}")
    public String deleteCustomerPage(@PathVariable(value = "customerId") int customerId, Model model, RedirectAttributes redirectAttributes) throws Exception {

        Customer customer = customerService.fetchCustomer(customerId);

        model.addAttribute("customer", customer);
        if (customer == null) {

            redirectAttributes.addFlashAttribute("errorMessage", "Customer not found");
            return "redirect:/customers";
        }

        customerService.delete(customer);


        redirectAttributes.addFlashAttribute("successMessage", "Customer deleted successfully");
        return "redirect:/customers";

    }

    @GetMapping(value="/adoptions/add/{dogId}/{customerId}")
    public String addAdoption(@PathVariable(value = "dogId") int dogId, @PathVariable(value = "customerId") int customerId, RedirectAttributes redirectAttributes) {
        try {

            Dog saveDog = dogService.fetchDog(dogId);
            Customer saveCustomer = customerService.fetchCustomer(customerId);

            Adoption newAdoption = new Adoption();
            newAdoption.setCustomer(saveCustomer);
            newAdoption.setDog(saveDog);

            adoptionService.save(newAdoption);


            redirectAttributes.addFlashAttribute("successMessage", "Adoption added successfully!");
        } catch (Exception e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add dog.");
        }

        return "adoptions";
    }

    @GetMapping("/adoptions/edit")
    public String adoptionsEdit(RedirectAttributes redirectAttributes, Model model) {
        List<Adoption> adoptionsList = adoptionService.findAll();
        model.addAttribute("adoptionsList", adoptionsList);
        return "editadoptions";
    }

    @PostMapping("/adoptions/update/{adoptionId}/{dogId}/{customerId}")
    public String updateAdoption(@PathVariable("adoptionId") int adoptionId, @PathVariable(value = "dogId") int dogId, @PathVariable(value = "customerId") int customerId, @ModelAttribute Adoption adoption) {
        try {

            Dog getDog = dogService.fetchDog(dogId);
            Customer getCustomer = customerService.fetchCustomer(customerId);
            Adoption existingAdoption = adoptionService.fetchAdoption(adoptionId);


            existingAdoption.setCustomer(getCustomer);
            existingAdoption.setDog(getDog);


            adoptionService.save(existingAdoption);

            return "redirect:/adoptions";
        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
    }

    @PostMapping("/adoptions/adoptionupdate/{adoptionId}")
    public String updateAdoption(@PathVariable("adoptionId") int adoptionId, Model model) {
        try {

            Adoption existingAdoption = adoptionService.fetchAdoption(adoptionId);

            model.addAttribute("adoption", existingAdoption);

            return "/updateadoptions";
        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
    }
    @PostMapping(value="/api/adoptions/add", consumes="application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<Adoption> createAdoption(@RequestBody Adoption adoption, @RequestParam("customerEmail") String customerEmail) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            Customer customer = customerService.findByEmail(customerEmail);
            if (customer != null) {
                adoption.setCustomer(customer);
                Adoption newAdoption = adoptionService.save(adoption);
                return ResponseEntity.ok(newAdoption);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}