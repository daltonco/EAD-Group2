package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Customer;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.dto.LabelValue;
import com.adoptmeplus.enterprise.service.ICustomerService;
import com.adoptmeplus.enterprise.service.IDogService;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
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

    // Everything in this section is for page / route mapping

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

    @RequestMapping("/dogs/update")
    public String updatedogs() { return "updatedog"; }

    @RequestMapping("/customers/update")
    public String updatecustomers() { return "updatecustomer"; }

// Everything in this section is for REST services


// Dog REST services

    /**
     * Handles a GET request to fetch a list of all dogs and adds it to a model.
     * @return The dogs page.
     */

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
     * Handles a POST request to add a new Dog to the database via a form
     *
     * @param dog The object representation of the Dog to be added.
     * @return A redirect to the /dogs page.
     */

    @PostMapping(value="/dogs/add")
    public String addDog(@ModelAttribute Dog dog) {
        try {
            dogService.save(dog);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/dogs";
    }

    /**
     * Handles a POST request to update a Dog by data passed from /dogs/update. It retrieves an existing Dog from the database
     * based on its unique identifier, and if the Dog is found, it updates the specified fields with the new values provided.
     * Only the fields with updated values are modified, and the others remain unchanged.
     *
     * @param dogId The unique identifier of the Dog to be updated.
     * @param dog The object representation of the Dog to be updated.
     * @return A redirect to the /dogs page.
     */

    @PostMapping("/dogs/update/updateDog")
    public String updateDog(@RequestParam("dogId") int dogId, @ModelAttribute Dog dog) {
        try {
            Dog existingDog = dogService.fetchDog(dogId);
            if (existingDog == null) {
                return "redirect:/error";
            }
            existingDog.setDogId((dog.getDogId()));
            existingDog.setFullName(dog.getFullName());
            existingDog.setAge(dog.getAge());
            existingDog.setBreed(dog.getBreed());
            existingDog.setLocation(dog.getLocation());
            existingDog.setTags(dog.getTags());

            dogService.save(existingDog);

            return "redirect:/dogs";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    /**
     * Handles a GET request to fetch information about a specific Dog by its unique identifier.
     * This method takes the `dogId` as a path variable and attempts to retrieve the corresponding Dog's information
     * using the dogService fetchDog method. If a Dog is found, it adds the data to a model of a Dog object.
     *
     * @param dogId The unique identifier of the Dog to fetch.
     * @return updateDog route with appropriate data added to 'Dog' object model.
     */

    @GetMapping("/dogs/update/{dogId}")
    public String updateDogPage(@PathVariable(value = "dogId") int dogId, Model model) {
        try {
            Dog dog = dogService.fetchDog(dogId);
            if (dog == null) {
                return "redirect:/error";
            }
            model.addAttribute("dog", dog);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "updatedog";
    }

    /**
     * Handles a DELETE request of a Dog by the Dog's unique identifier.
     * This method handles a DELETE request to delete a Dog by its unique identifier via form request.
     * It attempts to retrieve the existing Dog from the database and, if found, deletes the Dog.
     *
     * @param dogId The unique identifier of the Dog to be deleted.
     * @param dog The object representation of the Dog to be added.
     * @return A redirect to the /dogs page.
     */

    @DeleteMapping("/dogs/update/delete")
    public String deleteDog(@RequestParam("dogId") int dogId, @ModelAttribute Dog dog) {
        try {
            Dog existingDog = dogService.fetchDog(dogId);
            if (existingDog == null) {
                return "redirect:/error";
            }
            dogService.delete(existingDog);
            return "redirect:/dogs";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    /**
     * Handles Autocomplete for the dogs/edit page.
     * Uses dogService method findAutocompletebyBreed to create a list of Dogs by breed based on a term the user types.
     * Adds the information to labelValue to be displayed by frontend and sorts the information by label using Comparator.
     *
     * @param term Term that user types to be used for searching for breeds.
     * @return allDogBreeds, a List<LabelValue> containing all Dogs found by breed and their corresponding unique identifiers.
     */
    @GetMapping("dogs/edit/dogNamesAutocomplete")
    @ResponseBody
    public List<LabelValue> dogNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
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

// Customer REST services.

    /**
     * Handles a GET request to fetch a list of all Customers and adds it to a model.
     *
     * @return The customers page.
     */
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

    /**
     * Handles a POST request to add a new Customer to the database via a form.
     *
     * @param customer The object representation of the Customer to be added.
     * @return A redirect to the /customers page.
     */
    @PostMapping(value="/customers/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        try {
            customerService.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/customers";
    }

    /**
     * Handles a POST request to update a Customer by data passed from /customers/update. It retrieves an existing Customer from the database
     * based on its unique identifier, and if the Customer is found, it updates the specified fields with the new values provided.
     * Only the fields with updated values are modified, and the others remain unchanged.
     *
     * @param customerId The unique identifier of the Customer to be updated.
     * @param customer The object representation of the Customer to be updated.
     * @return A redirect to the /customers page.
     */
    @PostMapping("/customers/update/updateCustomer")
    public String updateCustomer(@RequestParam("customerId") int customerId, @ModelAttribute Customer customer) {
        try {
            Customer existingCustomer = customerService.fetchCustomer(customerId);
            if (existingCustomer == null) {
                return "redirect:/error";
            }
            existingCustomer.setCustomerId((customer.getCustomerId()));
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());

            customerService.save(existingCustomer);

            return "redirect:/customers";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    /**
     * Handles a GET request to fetch information about a specific Customer by its unique identifier.
     * This method takes the `customerId` as a path variable and attempts to retrieve the corresponding Customer's information
     * using the customerService fetchCustomer method. If a Customer is found, it adds the data to a model of a Customer object.
     *
     * @param customerId The unique identifier of the Customer to fetch.
     * @return updateDog route with appropriate data added to 'Customer' object model.
     */
    @GetMapping("/customers/update/{customerId}")
    public String updateCustomerPage(@PathVariable(value = "customerId") int customerId, Model model) {
        try {
            Customer customer = customerService.fetchCustomer(customerId);
            if (customer == null) {
                return "redirect:/error";
            }
            model.addAttribute("customer", customer);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "updatecustomer";
    }

    /**
     * Handles a DELETE request of a Customer by the Customer's unique identifier.
     * This method handles a DELETE request to delete a Customer by its unique identifier via form request.
     * It attempts to retrieve the existing Customer from the database and, if found, deletes the Customer.
     *
     * @param customerId The unique identifier of the Customer to be deleted.
     * @param customer The object representation of the Customer to be added.
     * @return A redirect to the /customers page.
     */
    @DeleteMapping("/customers/update/delete")
    public String deleteCustomer(@RequestParam("customerId") int customerId, @ModelAttribute Customer customer) {
        try {
            Customer existingCustomer = customerService.fetchCustomer(customerId);
            if (existingCustomer == null) {
                return "redirect:/error";
            }
            customerService.delete(existingCustomer);
            return "redirect:/customers";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    /**
     * Handles Autocomplete for the customers/edit page.
     * Uses customerService method findAutocompletebyEmail to create a list of Customers by e-mail based on a term the user types.
     * Adds the information to labelValue to be displayed by frontend and sorts the information by label using Comparator.
     *
     * @param term Term that user types to be used for searching for breeds.
     * @return allDogBreeds, a List<LabelValue> containing all Dogs found by breed and their corresponding unique identifiers.
     */
    @GetMapping("customers/edit/customerNamesAutocomplete")
    @ResponseBody
    public List<LabelValue> customerNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
        List<LabelValue> allCustomerEmails = new ArrayList<>();
        try {
            List<Customer> customers = customerService.findAutocompleteByEmail(term);
            for (Customer customer : customers) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(customer.getEmail() + " (" + customer.getLastName() + ", " + customer.getFirstName() + ")");
                labelValue.setValue(customer.getCustomerId());
                allCustomerEmails.add(labelValue);
                allCustomerEmails.sort(Comparator.comparing(LabelValue::getLabel));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return allCustomerEmails;
    }

// Adoption REST services.

    /**
     * Handles a GET request to fetch a list of all Adoptions and adds it to a model.
     *
     * @return The adoptions page.
     */
    @GetMapping("/adoptions")
    public String showAdoptions(Model model) {
        try {
            List<Adoption> adoptionsList = adoptionService.findAll();
            model.addAttribute("adoptionsList", adoptionsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adoptions";
    }

    /**
     * Handles a POST request to add a new Adoption to the database via path variables.
     * Uses dogService fetchDog method and customerService fetchCustomer method to fetch a Dog and a Customer
     * to be added to an Adoption. Since this joins both objects together by column and utilizes the primary keys of the corresponding
     * Dog and Customer, it is not currently possible to edit or delete an Adoption.
     *
     * @param dogId The unique identifier of the Dog to be added to the adoption.
     * @param customerId  The unique identifier of the Customer to be added to the adoption.
     * @return A redirect to the /adoptions page.
     */

    @GetMapping("/adoptions/add/{dogId}/{customerId}")
    public String addAdoption(@PathVariable(value = "dogId") int dogId, @PathVariable(value = "customerId") int customerId) {
        try {
            Dog getDog = dogService.fetchDog(dogId);
            Customer getCustomer = customerService.fetchCustomer(customerId);
            Adoption newAdoption = new Adoption();
            newAdoption.setCustomer(getCustomer);
            newAdoption.setDog(getDog);
            adoptionService.save(newAdoption);
            return "redirect:/adoptions";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}