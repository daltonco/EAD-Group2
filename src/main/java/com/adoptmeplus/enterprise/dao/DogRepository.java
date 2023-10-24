package com.adoptmeplus.enterprise.dao;

import com.adoptmeplus.enterprise.dto.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Integer> {
}
