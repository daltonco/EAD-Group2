package com.adoptmeplus.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdoptMePlusController {

    /**
     * handle the REST endpoint and return index page
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
