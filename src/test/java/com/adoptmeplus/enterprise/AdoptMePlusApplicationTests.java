package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.service.IAdoptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.adoptmeplus.enterprise.dto.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The `AdoptMePlusApplicationTests` class contains unit tests for various aspects of the AdoptMePlus application.
 *
 * It uses JUnit 5 for testing and Spring Boot's `@SpringBootTest` annotation for testing within a Spring Boot context.
 * The tests cover adoption properties, adding and removing adoptions, and more.
 */
@SpringBootTest(classes = AdoptMePlusApplication.class)
class AdoptMePlusApplicationTests {

    @MockBean
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
        Adoption adoption = new Adoption();
        adoption.setAdoptionId(AdoptionId);
        assertEquals(AdoptionId, adoption.getAdoptionId());

        int DogId = 1;
        Dog dog = new Dog();
        dog.setDogId(DogId);
        adoption.setDog(dog);
        assertEquals(DogId, adoption.getDog().getDogId());

        int CustomerId = 1;
        Customer customer = new Customer();
        customer.setCustomerId(CustomerId);
        adoption.setCustomer(customer);
        assertEquals(CustomerId, adoption.getCustomer().getCustomerId());
    }

    /**
     * Verifies the process of adding and removing adoption records.
     */
    @Test
    void verifyAddAndRemoveAdoptions() {
        int AdoptionId = 1;
        int DogId = 1;
        int CustomerId = 1;

        Adoption adoption = new Adoption();
        adoption.setAdoptionId(AdoptionId);
        adoption.getDog().setDogId(DogId);
        adoption.getCustomer().setCustomerId(CustomerId);

        adoptionService.save(adoption);

        List<Adoption> adoptions = adoptionService.findAll();
        boolean adoptionPresent = false;
        for (Adoption a : adoptions) {
            if (a.getDog().getDogId() == DogId && a.getCustomer().getCustomerId() == CustomerId) {
                adoptionPresent = true;
                break;
            }
        }

        assertTrue(adoptionPresent);
    }

}
