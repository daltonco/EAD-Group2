package com.adoptmeplus.enterprise;

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

import java.io.IOException;
import java.util.List;

@Controller
public class AdoptMePlusController {

    @Autowired
    IAdoptionService adoptionService;

    /**
     * handle the REST endpoint and return index page
     */
    @RequestMapping("/")
    public String index() {
        return "index";
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
