package com.example.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class Address extends BaseEntity {
    private String city;

    private String postalCode;

    private String street;
}