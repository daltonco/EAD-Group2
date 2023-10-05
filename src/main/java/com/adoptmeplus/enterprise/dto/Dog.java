package com.adoptmeplus.enterprise.dto;

import lombok.Data;

public @Data class Dog {
    // TODO: Change the names of the variables to be more easily identifiable? (e.g. Breed -> DogBreed)
    private int DogId;
    private String Breed;
    private String FullName;
}
