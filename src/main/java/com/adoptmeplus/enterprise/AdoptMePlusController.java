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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Controller
public class AdoptMePlusController {

    @Autowired
    IAdoptionService adoptionService;
  
    @RequestMapping("/")
    public String index() {
        return "index";
    }
  
    @GetMapping("/")
    @ResponseBody
    public List<Adoption> fetchAllAdoptions(){
        return adoptionService.fetchAll();
    }
  
    @GetMapping("/{id}")
    public ResponseEntity fetchAdoptionsById(@PathVariable("adoptionID") String id){
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
    @PostMapping(value="/", consumes="application/json", produces="application/json")
    public Adoption createAdoption(@RequestBody Adoption adoption){
        Adoption newAdoption = null;
        try{
            newAdoption = adoptionService.save(adoption);
        } catch (Exception e){
            // TODO add logging
        }
        return newAdoption;
    }
    @DeleteMapping("/{id}/")
    public ResponseEntity deleteAdoption(@PathVariable("adoptionID") String id){
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
    public ResponseEntity searchDogs(@RequestParam(value="searchTerm", required = false, defaultValue = "None") String searchTerm) {
        try {
            List<Dog> dogs = adoptionService.fetchDogs(searchTerm);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(dogs, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
