package com.orakul0187.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    String street;
    String city;
    String state;
}
