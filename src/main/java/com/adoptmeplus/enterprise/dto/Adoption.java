package com.adoptmeplus.enterprise.dto;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The Adoption class represents the adoption of a dog by a customer in the AdoptMePlus application.
 * This class is a Data Transfer Object (DTO) and includes properties such as AdoptionId, DogId, and CustomerId
 * to capture relevant information about an adoption record.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Entity
public @Data class Adoption {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int adoptionId;
    @OneToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    @OneToOne
    @JoinColumn(name = "customer_Id")
    private Customer customer;
}
