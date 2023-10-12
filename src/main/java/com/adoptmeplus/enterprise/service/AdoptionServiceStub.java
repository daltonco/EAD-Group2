package com.adoptmeplus.enterprise.service;

<<<<<<< HEAD
import com.adoptmeplus.enterprise.dao.IAdoptionDAO;
import com.adoptmeplus.enterprise.dao.IDogDAO;
import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
=======
import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.stereotype.Service;

>>>>>>> origin/main
import java.util.List;

@Service
public class AdoptionServiceStub implements IAdoptionService{

<<<<<<< HEAD
    @Autowired
    private IAdoptionDAO adoptionDAO;

    private IDogDAO dogDAO;
    public AdoptionServiceStub(IAdoptionDAO adoptionDAO){
        this.adoptionDAO = adoptionDAO;
    }
=======
    public AdoptionServiceStub(){}
>>>>>>> origin/main
    @Override
    public Adoption save(Adoption adoption) {
        return null;
    }

    @Override
    public List<Dog> fetchAll() {
        return null;
    }
<<<<<<< HEAD

    @Override
    public List<Dog> fetchDogs(String age) throws IOException {
        return dogDAO.fetchDogs(age);
    }
=======
>>>>>>> origin/main
}
