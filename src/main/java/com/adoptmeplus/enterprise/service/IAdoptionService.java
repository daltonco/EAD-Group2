package com.adoptmeplus.enterprise.service;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;

<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> origin/main
import java.util.List;

public interface IAdoptionService {


    Adoption save(Adoption adoption);
    List<Dog> fetchAll();
<<<<<<< HEAD
    List<Dog> fetchDogs(String age) throws IOException;
=======
>>>>>>> origin/main
}
