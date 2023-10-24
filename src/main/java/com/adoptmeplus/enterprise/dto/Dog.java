package com.adoptmeplus.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
}


