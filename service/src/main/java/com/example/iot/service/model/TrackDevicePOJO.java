package com.example.iot.service.model;

import com.example.iot.domain.entity.Sim;
import com.example.iot.service.constants.DeviceStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDevicePOJO {
    private long trackDeviceId;
    private DeviceStatusEnum deviceStatusEnum;
    private Long trackDeviceTemp;
    private SimPOJO simPOJO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;

    public TrackDevicePOJO(long trackDeviceId, long trackDeviceTemp, Sim simByTrackDeviceSimId, long trackDeviceStatus) {
        this.trackDeviceId = trackDeviceId;
        this.trackDeviceTemp = trackDeviceTemp;
        this.simPOJO = new SimPOJO(simByTrackDeviceSimId);
        this.deviceStatusEnum = DeviceStatusEnum.getStatusEnumFromId(trackDeviceStatus);
    }


}
