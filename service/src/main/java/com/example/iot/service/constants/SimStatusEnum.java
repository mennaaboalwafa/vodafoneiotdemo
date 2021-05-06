package com.example.iot.service.constants;

public enum SimStatusEnum {
    INACTIVE(0L), ACTIVE(1L), DEACTIVATE(2L);

    private final Long statusId;

    SimStatusEnum(Long statusId) {
        this.statusId = statusId;
    }

    public static SimStatusEnum getStatusEnumFromId(Long statusId) {
        for (SimStatusEnum status : values()) {
            if (status.statusId.equals(statusId)) {
                return status;
            }
        }
        return null;
    }
}
