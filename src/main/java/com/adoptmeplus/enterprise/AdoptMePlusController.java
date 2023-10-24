package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.service.IDogService;
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
public class AdoptMePlusController {


    private final IDogService adoptionService;
    @Autowired
    public AdoptMePlusController(IDogService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
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

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity searchDogs(@RequestParam(value="searchTerm", required = false, defaultValue = "None") String searchTerm) {
        try {
            List<Dog> dogs = adoptionService.fetchDog(searchTerm);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(dogs, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
