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

    @RequestMapping("/dogs/update")
    public String updatedogs() { return "updatedog"; }

    @RequestMapping("/customers/update")
    public String updatecustomers() { return "updatecustomer"; }

    /*

    Everything under this section is for REST services!!

     */

            /*

            Dog Rest services

             */

    /**
     * Handles a GET request to fetch a list of all dogs.
     *
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
     * Handles a POST request to add a new dog to the system.
     * @param dog The JSON representation of the dog to be added.
     * @return The dogs page.
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
     * Updates an existing Dog with the provided information.
     * This method handles a POST request to update a Dog by its unique identifier. It retrieves the existing Dog from the database,
     * and if the Dog is found, it updates the specified fields with the new values provided in the request body. Only the fields
     * with updated values are modified, and the others remain unchanged.
     *
     * @param dogId The unique identifier of the Dog to be updated.
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
     * Handles a GET request to fetch information about a specific dog by its unique identifier.
     * This method takes the `dogId` as a path variable and attempts to retrieve the corresponding dog's information
     * using `dogService`. If the dog is found, it returns a response containing the dog's information with an
     * HTTP status of 200 (OK). If an error occurs during the fetch operation, it returns an error response with an
     * HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param dogId The unique identifier of the dog to fetch.
     * @return A ResponseEntity containing either the fetched dog's information or an error response.
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
     * Deletes a Dog resource by its unique identifier.
     * This method handles a DELETE request to delete a Dog by its unique identifier. It attempts to retrieve the existing Dog from
     * the database and, if found, deletes the Dog. If the Dog is not found, a 404 Not Found response is returned. If an error occurs
     * during the deletion, a 500 Internal Server Error response is returned.
     *
     * @param dogId The unique identifier of the Dog to be deleted.
     * @return A ResponseEntity indicating the success of the deletion or an error response.
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

            /*

            Customer REST services

             */

    /**
     * Handles a GET request to fetch a list of all customers.
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
     * Handles a POST request to add a new customer to the system.
     * @param customer The customer to be added.
     * @return The customers page.
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
     * Updates an existing Customer with the provided information.
     * This method handles a POST request to update a Dog by its unique identifier. It retrieves the existing Customer from the database,
     * and if the Customer is found, it updates the specified fields with the new values provided in the request body. Only the fields
     * with updated values are modified, and the others remain unchanged.
     *
     * @param customerId The unique identifier of the Customer to be updated.
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
     * Handles a GET request to fetch information about a specific dog by its unique identifier.
     * This method takes the `dogId` as a path variable and attempts to retrieve the corresponding dog's information
     * using `dogService`. If the dog is found, it returns a response containing the dog's information with an
     * HTTP status of 200 (OK). If an error occurs during the fetch operation, it returns an error response with an
     * HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param customerId The unique identifier of the dog to fetch.
     * @return A ResponseEntity containing either the fetched dog's information or an error response.
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
     * Deletes a Customer resource by its unique identifier.
     * This method handles a DELETE request to delete a Customer by its unique identifier. It attempts to retrieve the existing Customer from
     * the database and, if found, deletes the Customer. If an error occurs during the deletion the user is redirected to an error page.
     *
     * @param customerId The unique identifier of the Customer to be deleted.
     * @return A ResponseEntity indicating the success of the deletion or an error response.
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

            /*

            Adoption REST services

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