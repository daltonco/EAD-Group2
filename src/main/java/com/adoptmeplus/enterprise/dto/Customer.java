package com.adoptmeplus.enterprise.dto;

import lombok.Data;

public @Data class Customer {
    private int CustomerId;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Address;

}
