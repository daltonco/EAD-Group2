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

@Controller
public class AdoptMePlusController {


    private final IAdoptionService adoptionService;
    private final IDogService dogService;
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
     * Create a new adoption object, given the data provided.
     * returns one of the following:
     * - successfully created a new adoption
     * - unable to create an adoption, it already exists
     * @param adoption a JSON representation of an adoption object.
     * @return the newly created adoption object
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