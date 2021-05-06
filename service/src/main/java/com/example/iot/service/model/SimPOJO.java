package com.example.iot.service.model;

import com.example.iot.domain.entity.Sim;
import com.example.iot.service.constants.SimStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimPOJO {
    private long simId;
    private String simOperatorCode;
    private SimStatusEnum simStatus;
    private CountryPOJO country;
    private String simNum;

    public SimPOJO(Sim simByTrackDeviceSimId) {
        this.simId = simByTrackDeviceSimId.getSimId();
        this.simStatus = SimStatusEnum.getStatusEnumFromId(simByTrackDeviceSimId.getSimStatus());
        this.simOperatorCode = simByTrackDeviceSimId.getSimOperatorCode();
        this.simNum = simByTrackDeviceSimId.getSimNum();
        this.country = new CountryPOJO(simByTrackDeviceSimId.getCountriesBySimCountryId().getCountryName());

    }

    public SimPOJO(long simId, String simOperatorCode, SimStatusEnum simStatus, String simNum, CountryPOJO country) {
        this.simId = simId;
        this.simStatus = simStatus;
        this.simOperatorCode = simOperatorCode;
        this.simNum = simNum;
        this.country = country;
    }
}
