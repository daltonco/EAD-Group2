package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.service.IDogService;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.List;

/**
 * The `AdoptMePlusController` class serves as the controller for managing the AdoptMePlus enterprise system's web application.
 *
 * This class handles requests related to adoptions, dog management, searching, and general pages such as the index, contact, and search pages.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Controller
public class AdoptMePlusController {


    private final IAdoptionService adoptionService;
    private final IDogService dogService;

    /**
     * Constructs an `AdoptMePlusController` with the specified services for adoption and dog management.
     *
     * @param adoptionService The service for managing adoption records.
     * @param dogService The service for managing dog-related operations.
     */
    @Autowired
    public AdoptMePlusController(IAdoptionService adoptionService, IDogService dogService) {
        this.adoptionService = adoptionService;
        this.dogService = dogService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/adoptions")
    public String adoptions(){
        return "adoptions";
    }
    @GetMapping("/adoptions/all")
    @ResponseBody
    public List<Adoption> fetchAllAdoptions(){
        return adoptionService.fetchAll();
    }

    @GetMapping("/adoptions/{id}")
    public ResponseEntity fetchAdoptionsById(@PathVariable("id") String id){
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Handles a POST request to create a new adoption record.
     *
     * This method receives a JSON representation of an adoption object in the request body and attempts to save it using
     * the `adoptionService`. If the adoption record is successfully created, it returns the newly created adoption object
     * with an HTTP status of 200 (OK). If an error occurs during the creation process, it returns an error response.
     *
     * @param adoption The JSON representation of the adoption object to be created.
     * @return A ResponseEntity containing either the newly created adoption object or an error response.
     */
    @PostMapping(value="/adoptions/create", consumes="application/json", produces="application/json")
    @ResponseBody
    public Adoption createAdoption(@RequestBody Adoption adoption){
        Adoption newAdoption = null;
        try{
            newAdoption = adoptionService.save(adoption);
        } catch (Exception e){
            // TODO add logging
        }
        return newAdoption;
    }
    @DeleteMapping("/adoptions/delete/{id}")
    public ResponseEntity deleteAdoption(@PathVariable("id") String id){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/dogs")
    public String dogs() {
        return "dogs";
    }

    /**
     * Handles a POST request to add a new dog to the system.
     *
     * This method receives a JSON representation of a dog object in the request body and attempts to save it using the `dogService`.
     *
     * If the dog is successfully added, it returns a response containing the newly created dog with an HTTP status of 200 (OK).
     * If an error occurs during the addition process, it returns an error response with an HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param dog The JSON representation of the dog to be added.
     * @return A ResponseEntity containing either the newly created dog or an error response.
     */
    @PostMapping(value="/dogs/add", consumes="application/json", produces="application/json")
    public ResponseEntity addDog(@RequestBody Dog dog) {
        Dog newDog = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            newDog = dogService.save(dog);
        } catch (Exception e) {

            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newDog, headers, HttpStatus.OK);
    }

    /**
     * Handles a GET request to fetch information about a specific dog by its unique identifier.
     *
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

    /**
     * Handles a GET request to search for dogs based on a provided search term.
     *
     * This method allows users to search for dogs based on a specified search term. The search term is passed as a query
     * parameter, and the method attempts to retrieve a list of dogs that match the search criteria using the `dogService`.
     * If dogs are found, it returns a response containing the list of dogs with an HTTP status of 200 (OK). If an error
     * occurs during the search operation, it returns an error response with an HTTP status of 500 (INTERNAL_SERVER_ERROR).
     *
     * @param searchTerm The search term used to filter and find matching dogs (default is "None" if not provided).
     * @return A ResponseEntity containing either the list of matching dogs or an error response.
     */
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity searchDogs(@RequestParam(value="searchTerm", required = false, defaultValue = "None") String searchTerm) {
        try {
            List<Dog> dogs = dogService.fetchAll(searchTerm);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(dogs, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}