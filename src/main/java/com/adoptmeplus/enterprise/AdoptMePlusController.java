package com.adoptmeplus.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdoptMePlusController {
    @GetMapping("/")
    public String index() {
        return "index";
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
