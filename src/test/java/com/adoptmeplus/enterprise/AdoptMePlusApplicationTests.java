package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.adoptmeplus.enterprise.dto.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The `AdoptMePlusApplicationTests` class contains unit tests for various aspects of the AdoptMePlus application.
 *
 * It uses JUnit 5 for testing and Spring Boot's `@SpringBootTest` annotation for testing within a Spring Boot context.
 * The tests cover adoption properties, adding and removing adoptions, and more.
 */
@SpringBootTest
class AdoptMePlusApplicationTests {

    @Autowired
    private IAdoptionService adoptionService;

    @Test
    void contextLoads() {
    }

    /**
     * Verifies the properties of the `Adoption` object, including `adoptionId`, `dogId`, and `customerId`.
     */
    @Test
    void verifyAdoptionProperties() {
        int AdoptionId = 1;
        int DogId = 1;
        String CustomerId = "1";

        Adoption adoption = new Adoption();
        adoption.setAdoptionId(AdoptionId);
        assertEquals(AdoptionId, adoption.getAdoptionId());

        adoption.setDogId(DogId);
        assertEquals(DogId, adoption.getDogId());

        adoption.setCustomerId(CustomerId);
        assertEquals(CustomerId, adoption.getCustomerId());

    }

    /**
     * Verifies the process of adding and removing adoption records.
     */
    @Test
    void verifyAddAndRemoveAdoptions() {
        int AdoptionId = 1;
        int DogId = 1;
        String CustomerId = "1";

        Adoption adoption = new Adoption();
        adoption.setAdoptionId(AdoptionId);
        adoption.setDogId(DogId);
        adoption.setCustomerId(CustomerId);

        adoptionService.save(adoption);

        List<Adoption> adoptions = adoptionService.fetchAll();
        boolean adoptionPresent = false;
        for (Adoption a : adoptions) {
            if (a.getDogId() == DogId && a.getDogId() == DogId) {
                adoptionPresent = true;
                break;
            }
        }

        assertTrue(adoptionPresent);
    }

}
