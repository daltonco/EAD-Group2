package com.adoptmeplus.enterprise.dto;

import lombok.Data;

public @Data class Adoption {
    private int AdoptionId;
    private int DogId;
    private String CustomerId;
}
