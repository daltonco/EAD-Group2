package com.adoptmeplus.enterprise.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

public @Data class Dog {
    @SerializedName("dogId")
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


