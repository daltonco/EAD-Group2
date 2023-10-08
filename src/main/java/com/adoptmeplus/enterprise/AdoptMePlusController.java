package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdoptMePlusController {

    @Autowired
    IAdoptionService adoptionService;
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/")
    public ResponseEntity fetchAllAdoptions(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity fetchAdoptionsById(@PathVariable("adoptionID") String id){
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping(value="/", consumes="application/json", produces="application/json")
    public Adoption createAdoption(@RequestBody Adoption adoption){
        return adoption;
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
