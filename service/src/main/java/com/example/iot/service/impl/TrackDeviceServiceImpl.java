package com.example.iot.service.impl;

import com.example.iot.domain.entity.TrackDevice;
import com.example.iot.persistence.dao.ifc.TrackDeviceDao;
import com.example.iot.service.constants.DeviceStatusEnum;
import com.example.iot.service.ifc.TrackDeviceService;
import com.example.iot.service.model.TrackDevicePOJO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TrackDeviceServiceImpl implements TrackDeviceService {

    private final TrackDeviceDao trackDeviceDao;

    public TrackDeviceServiceImpl(TrackDeviceDao trackDeviceDao) {
        this.trackDeviceDao = trackDeviceDao;
    }

    @Override
    public Page<TrackDevicePOJO> findInactiveDevices() {
        // pageable object to return 10 values for each page
        return mapTrackDeviceTOPOJO(trackDeviceDao.findInactiveDevices(PageRequest.of(0, 10)));
    }

    @Override
    public TrackDevicePOJO updateDeviceStatusConfig(Long trackDeviceId, DeviceStatusEnum deviceStatusEnum) {
        TrackDevicePOJO trackDevicePOJO = new TrackDevicePOJO();
        //get TrackDevice with id
        TrackDevice trackDevice = trackDeviceDao.get(trackDeviceId).orElse(null);
        if (trackDevice == null) {

            trackDevicePOJO.setErrorMessage("TrackDeviceId not found");
            return trackDevicePOJO;
        }
        //configure device with ready
        else if (deviceStatusEnum.getStatusId() == 1) {
            String errorMessage = validateDeviceForUpdate(trackDevice);
            if (errorMessage != null) {
                trackDevicePOJO.setErrorMessage(errorMessage);
                return trackDevicePOJO;
            }
        }
        // update track device
        trackDevice.setTrackDeviceStatus(deviceStatusEnum.getStatusId());
        TrackDevice updated = trackDeviceDao.save(trackDevice);
        return new TrackDevicePOJO(updated.getTrackDeviceId(), updated.getTrackDeviceTemp(), updated.getSimByTrackDeviceId(), updated.getTrackDeviceStatus());
    }

    @Override
    public boolean delete(Long trackDeviceId) {

        return trackDeviceDao.delete(trackDeviceId);
    }

    @Override
    public Page<TrackDevicePOJO> findActiveDevices() {
        //make sure its active and temp between (-25 and 85) and sim is active
        return mapTrackDeviceTOPOJO(trackDeviceDao.findActiveDevices(PageRequest.of(0, 10)));
    }

    private Page<TrackDevicePOJO> mapTrackDeviceTOPOJO(Page<TrackDevice> inactiveDevices) {
        return inactiveDevices.map(trackDevice -> new TrackDevicePOJO(trackDevice.getTrackDeviceId(), trackDevice.getTrackDeviceTemp()
                , trackDevice.getSimByTrackDeviceId(), trackDevice.getTrackDeviceStatus()));
    }

    private String validateDeviceForUpdate(TrackDevice trackDevice) {
        //check if the temp between (-25 , 85C ) to update the device status to ready
        if (trackDevice.getTrackDeviceTemp() <= -25 && trackDevice.getTrackDeviceTemp() >= 85) {
            return "temperature must be between -25 and 85 the temp now is " + trackDevice.getTrackDeviceTemp();
        } else if (trackDevice.getSimByTrackDeviceId().getSimStatus() == 0 || trackDevice.getSimByTrackDeviceId().getSimStatus() == 2) {
            return "Sim card is not active or waiting to be activated";
        }
        return null;
    }
}
