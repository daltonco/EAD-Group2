package com.adoptmeplus.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The `AdoptMePlusApplication` class is the entry point for the AdoptMePlus enterprise system.
 *
 * This Spring Boot application class initializes and runs the AdoptMePlus application, allowing it to
 * be started as a standalone Java application.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@SpringBootApplication
public class AdoptMePlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoptMePlusApplication.class, args);
    }

}
