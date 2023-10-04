package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dao.AdoptionDAOStub;
import com.adoptmeplus.enterprise.service.AdoptionServiceStub;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.adoptmeplus.enterprise.dto.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class AdoptMePlusApplicationTests {

    @Test
    void contextLoads() {
    }

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

    @Test
    void verifyAddAndRemoveAdoptions() {
        int AdoptionId = 1;
        int DogId = 1;
        String CustomerId = "1";

        Adoption adoption = new Adoption();
        adoption.setAdoptionId(AdoptionId);
        adoption.setDogId(DogId);
        adoption.setCustomerId(CustomerId);

        AdoptionServiceStub adoptionService = new AdoptionServiceStub();
        adoptionService.save(adoption);

        List<Dog> adoptions = adoptionService.fetchAll();
        boolean adoptionPresent = false;
        for (Dog a : adoptions) {
            if (a.getDogId() == DogId && a.getDogId() == DogId) {
                adoptionPresent = true;
                break;
            }
        }

        assertTrue(adoptionPresent);
    }

}
