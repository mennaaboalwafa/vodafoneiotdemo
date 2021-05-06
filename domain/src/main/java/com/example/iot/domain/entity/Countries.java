package com.example.iot.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Countries {
    private String countryName;
    private long countryId;

    public Countries() {
    }

    public Countries(long countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    @Basic
    @Column(name = "COUNTRY_NAME")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Id
    @GeneratedValue(generator = "COUNTRIES_SEQ")
    @Column(name = "COUNTRY_ID")
    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Countries countries = (Countries) o;

        if (countryId != countries.countryId) return false;
        return Objects.equals(countryName, countries.countryName);
    }

    @Override
    public int hashCode() {
        int result = countryName != null ? countryName.hashCode() : 0;
        result = 31 * result + (int) (countryId ^ (countryId >>> 32));
        return result;
    }
}
