package com.example.iot.service.model;

import lombok.Data;

@Data
public class CountryPOJO {
    private String countryName;

    public CountryPOJO(String countryName) {
        this.countryName = countryName;
    }
}
