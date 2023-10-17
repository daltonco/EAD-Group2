package com.adoptmeplus.enterprise.service;
import com.adoptmeplus.enterprise.dao.IAdoptionDAO;
import com.adoptmeplus.enterprise.dao.IDogDAO;
import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class AdoptionServiceStub implements IAdoptionService{

    private final IAdoptionDAO adoptionDAO;
    private final IDogDAO dogDAO;
    @Autowired
    public AdoptionServiceStub(IAdoptionDAO adoptionDAO, IDogDAO dogDAO){
        this.adoptionDAO = adoptionDAO;
        this.dogDAO = dogDAO;
    }
  
    @Override
    public Adoption save(Adoption adoption) {

        return adoptionDAO.save(adoption);
    }

    @Override
    public List<Adoption> fetchAll() {

        return adoptionDAO.fetchAll();
    }

    @Override
    public List<Dog> fetchDogs(String age) throws IOException {
        return dogDAO.fetchDogs(age);
    }
}
