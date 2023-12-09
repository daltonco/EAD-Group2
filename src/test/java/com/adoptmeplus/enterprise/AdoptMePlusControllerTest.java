package com.adoptmeplus.enterprise;

import com.adoptmeplus.enterprise.AdoptMePlusController;
import com.adoptmeplus.enterprise.dto.Dog;
import com.adoptmeplus.enterprise.service.DogService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdoptMePlusControllerTest {

    @InjectMocks
    AdoptMePlusController adoptMePlusController;

    @Mock
    DogService dogService;

    @Test
    public void testAddDog() throws Exception {
        Dog dog = new Dog();
        when(dogService.save(dog)).thenReturn(dog);

        String result = adoptMePlusController.addDog(dog);

        assertEquals("redirect:/dogs", result);
        verify(dogService, times(1)).save(dog);
    }

    @Test
    public void testUpdateDog() throws Exception {
        Dog dog = new Dog();
        int dogId = 1;
        when(dogService.fetchDog(dogId)).thenReturn(dog);

        String result = adoptMePlusController.updateDog(dogId, dog);

        assertEquals("redirect:/dogs", result);
        verify(dogService, times(1)).fetchDog(dogId);
        verify(dogService, times(1)).save(dog);
    }

/*    @Test
    public void testFetchDog() throws IOException {
        Dog dog = new Dog();
        int dogId = 1;
        when(dogService.fetchDog(dogId)).thenReturn(dog);

        // TODO: Create a fetchDog method in AdoptMePlusController
        ResponseEntity<Dog> result = adoptMePlusController.fetchDog(dogId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(dog, result.getBody());
        verify(dogService, times(1)).fetchDog(dogId);
    }*/

    @Test
    public void testShowAllDogs() throws IOException {
        List<Dog> dogs = Arrays.asList(new Dog(), new Dog());
        when(dogService.findAll()).thenReturn(dogs);

        String result = adoptMePlusController.dogs();

        assertEquals("dogs", result);
        verify(dogService, times(1)).findAll();
    }

    @Test
    public void testRemoveDog() throws Exception {
        Dog dog = new Dog();
        int dogId = 1;

        String result = adoptMePlusController.deleteDog(dogId, dog);

        assertEquals("redirect:/dogs", result);
        verify(dogService, times(1)).delete(dog);
    }
}