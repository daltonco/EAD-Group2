package com.adoptmeplus.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;

/**
 * The `Customer` class represents a customer in the AdoptMePlus enterprise system.
 *
 * This class is used to store information about customers who are interested in adopting pets
 * from the system. It contains fields for the customer's identification, name, email, address,
 * and a reference to an associated adoption record, if applicable.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Entity
public @Data class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int customerId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("adoption")
    @OneToOne(mappedBy = "customer")
    private Adoption adoption;
}
