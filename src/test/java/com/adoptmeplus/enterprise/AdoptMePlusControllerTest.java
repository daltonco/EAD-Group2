package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Customer;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.service.IAdoptionService;
import com.adoptmeplus.enterprise.service.ICustomerService;
import com.adoptmeplus.enterprise.service.IDogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdoptMePlusControllerTest {

    @InjectMocks
    AdoptMePlusController adoptMePlusController;

    @Mock
    IAdoptionService adoptionService;

    @Mock
    IDogService dogService;

    @Mock
    ICustomerService customerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllDogs() throws IOException {
        Dog dog = new Dog();
        when(dogService.findAll()).thenReturn(Collections.singletonList(dog));

        ResponseEntity<List<Dog>> response = adoptMePlusController.findAllDogs(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(dogService, times(1)).findAll();
    }


    @Test
    public void testAddDog() throws Exception {
        Dog dog = new Dog();
        when(dogService.save(any(Dog.class))).thenReturn(dog);

        ResponseEntity<Dog> response = adoptMePlusController.addDog(dog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dogService, times(1)).save(any(Dog.class));
    }

    @Test
    public void testUpdateDog() throws Exception {
        Dog dog = new Dog();
        when(dogService.fetchDog(anyInt())).thenReturn(dog);
        when(dogService.save(any(Dog.class))).thenReturn(dog);

        ResponseEntity<Dog> response = adoptMePlusController.updateDog(1, dog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dogService, times(1)).fetchDog(anyInt());
        verify(dogService, times(1)).save(any(Dog.class));
    }

    @Test
    public void testFetchDog() throws IOException {
        Dog dog = new Dog();
        when(dogService.fetchDog(anyInt())).thenReturn(dog);

        ResponseEntity<Dog> response = adoptMePlusController.fetchDog(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dogService, times(1)).fetchDog(anyInt());
    }

    @Test
    public void testDeleteDog() throws Exception {
        Dog dog = new Dog();
        when(dogService.fetchDog(anyInt())).thenReturn(dog);
        doNothing().when(dogService).delete(any(Dog.class));

        ResponseEntity<String> response = adoptMePlusController.deleteDog(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dogService, times(1)).fetchDog(anyInt());
        verify(dogService, times(1)).delete(any(Dog.class));
    }

    @Test
    public void testCreateAdoption() throws IOException {
        Adoption adoption = new Adoption();
        Customer customer = new Customer();
        when(customerService.findByEmail(anyString())).thenReturn(customer);
        when(adoptionService.save(any(Adoption.class))).thenReturn(adoption);

        ResponseEntity<Adoption> response = adoptMePlusController.createAdoption(adoption, "test@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).findByEmail(anyString());
        verify(adoptionService, times(1)).save(any(Adoption.class));
    }
}