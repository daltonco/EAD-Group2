package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/adoptions")
public class AdoptMePlusController {

    @Autowired
    private IAdoptionService adoptionService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Adoption> fetchAllAdoptions() {
        return adoptionService.fetchAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity fetchAdoptionById(@PathVariable("id") String id) {
        // TODO: Implement fetching an adoption by ID
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create a new adoption object, given the data provided.
     * Returns one of the following:
     * - Successfully created a new adoption
     * - Unable to create an adoption, it already exists
     *
     * @param adoption a JSON representation of an adoption object.
     * @return the newly created adoption object
     */
    @PostMapping("/create")
    public ResponseEntity<Adoption> createAdoption(@RequestBody Adoption adoption) {
        try {
            Adoption newAdoption = adoptionService.save(adoption);
            return new ResponseEntity<>(newAdoption, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: Implement proper error handling and logging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdoption(@PathVariable("id") String id) {
        // TODO: Implement adoption deletion
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/searchDogs")
    public ResponseEntity<List<Dog>> searchDogs(@RequestParam(value = "searchTerm", required = false, defaultValue = "None") String searchTerm) {
        try {
            List<Dog> dogs = adoptionService.fetchDogs(searchTerm);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(dogs, headers, HttpStatus.OK);
        } catch (IOException e) {
            // TODO: Implement proper error handling and logging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
