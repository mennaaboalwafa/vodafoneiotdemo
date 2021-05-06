package com.example.iot.domain.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Sim {
    private long simId;
    private String simOperatorCode;
    private long simStatus;
    private String simNum;
    private Countries countriesBySimCountryId;

    public Sim() {
    }

    public Sim(long simId, String simOperatorCode, long simStatus, String simNum, Countries countriesBySimCountryId) {
        this.simId = simId;
        this.simOperatorCode = simOperatorCode;
        this.simStatus = simStatus;
        this.simNum = simNum;
        this.countriesBySimCountryId = countriesBySimCountryId;
    }

    @Id
    @GeneratedValue(generator = "SIM_SEQ")
    @Column(name = "SIM_ID")
    public long getSimId() {
        return simId;
    }

    public void setSimId(long simId) {
        this.simId = simId;
    }

    @Basic
    @Column(name = "SIM_OPERATOR_CODE")
    public String getSimOperatorCode() {
        return simOperatorCode;
    }

    public void setSimOperatorCode(String simOperatorCode) {
        this.simOperatorCode = simOperatorCode;
    }

    @Basic
    @Column(name = "SIM_STATUS")
    public long getSimStatus() {
        return simStatus;
    }

    public void setSimStatus(long simStatus) {
        this.simStatus = simStatus;
    }

    @Basic
    @Column(name = "SIM_NUM")
    public String getSimNum() {
        return simNum;
    }

    public void setSimNum(String simNum) {
        this.simNum = simNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sim sim = (Sim) o;

        if (simId != sim.simId) return false;
        if (!simOperatorCode.equals(sim.simOperatorCode)) return false;
        if (simStatus != sim.simStatus) return false;
        return Objects.equals(simNum, sim.simNum);
    }

    @Override
    public int hashCode() {
        int result = (int) (simId ^ (simId >>> 32));
        result = 31 * result + (simOperatorCode != null ? simOperatorCode.hashCode() : 0);
        result = 31 * result + (int) (simStatus ^ (simStatus >>> 32));
        result = 31 * result + (simNum != null ? simNum.hashCode() : 0);
        result = 31 * result + (countriesBySimCountryId != null ? countriesBySimCountryId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "SIM_COUNTRY_ID", referencedColumnName = "COUNTRY_ID", nullable = false)
    public Countries getCountriesBySimCountryId() {
        return countriesBySimCountryId;
    }

    public void setCountriesBySimCountryId(Countries countriesBySimCountryId) {
        this.countriesBySimCountryId = countriesBySimCountryId;
    }


}
