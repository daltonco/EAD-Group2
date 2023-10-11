package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/dogs")
    public String dogs() {
        return "dogs";
    }
    @GetMapping("/search")
    public String search() {
        return "search";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
