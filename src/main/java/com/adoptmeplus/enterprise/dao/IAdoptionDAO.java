package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Adoption;
import com.adoptmeplus.enterprise.dto.Dog;

import java.util.List;

public interface IAdoptionDAO {

    Adoption save(Adoption adoption);

    List<Adoption> fetchAll();
}
