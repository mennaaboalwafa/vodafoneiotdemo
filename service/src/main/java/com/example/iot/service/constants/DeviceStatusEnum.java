package com.example.iot.service.constants;

public enum DeviceStatusEnum {
    READY(1L), UNREADY(0L);

    private final Long statusId;

    DeviceStatusEnum(Long statusId) {
        this.statusId = statusId;
    }

    public static DeviceStatusEnum getStatusEnumFromId(Long statusId) {
        for (DeviceStatusEnum status : values()) {
            if (status.statusId.equals(statusId)) {
                return status;
            }
        }
        return null;
    }

    public Long getStatusId() {
        return statusId;
    }
}
