package com.adoptmeplus.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * The `Dog` class represents a dog available for adoption in the AdoptMePlus enterprise system.
 * This class stores information about dogs, including their identification, full name, breed, age,
 * tags, location, arrival date, and an associated adoption record, if applicable.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Entity
public @Data class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int dogId;
    @SerializedName("fullName")
    private String FullName;
    @SerializedName("breed")
    private String breed;
    @SerializedName("age")
    private int age;
    @SerializedName("tags")
    private String tags;
    @SerializedName("location")
    private String location;
    @SerializedName("dateArrived")
    private Date dateArrived;
    @SerializedName("adoption")
    @OneToOne(mappedBy = "dog")
    private Adoption adoption;
}


