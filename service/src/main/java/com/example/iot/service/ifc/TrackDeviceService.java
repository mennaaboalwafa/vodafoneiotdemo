package com.example.iot.service.ifc;

import com.example.iot.service.constants.DeviceStatusEnum;
import com.example.iot.service.model.TrackDevicePOJO;
import org.springframework.data.domain.Page;

public interface TrackDeviceService {
    Page<TrackDevicePOJO> findInactiveDevices();

    TrackDevicePOJO updateDeviceStatusConfig(Long trackDeviceId, DeviceStatusEnum deviceStatusEnum);

    boolean delete(Long trackDeviceId);

    Page<TrackDevicePOJO> findActiveDevices();
}
