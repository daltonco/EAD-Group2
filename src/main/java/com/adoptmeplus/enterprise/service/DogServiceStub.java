package com.adoptmeplus.enterprise.service;
import com.adoptmeplus.enterprise.dao.IDogDAO;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class DogServiceStub implements IDogService {

    private final IDogDAO dogDAO;
    @Autowired
    public DogServiceStub(IDogDAO dogDAO){
        this.dogDAO = dogDAO;
    }

    @Override
    public Dog save(Dog dog) throws Exception {
        return dogDAO.save(dog);
    }

    @Override
    public List<Dog> fetchAll(String breed) throws IOException {
        return dogDAO.fetchAll(breed);
    }

    @Override
    public Dog fetchDog(int dogId) throws IOException {
        return dogDAO.fetchDog(dogId);
    }
}
