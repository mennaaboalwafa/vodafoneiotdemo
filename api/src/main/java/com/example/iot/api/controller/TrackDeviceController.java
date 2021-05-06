package com.example.iot.api.controller;

import com.example.iot.service.constants.DeviceStatusEnum;
import com.example.iot.service.ifc.TrackDeviceService;
import com.example.iot.service.model.TrackDevicePOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/trackDevice", produces = "application/json")
public class TrackDeviceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackDeviceController.class);

    private static final String NOT_FOUND = "IdeaUsersVotes not found";

    private final TrackDeviceService trackDeviceService;

    @Autowired
    public TrackDeviceController(TrackDeviceService trackDeviceService) {
        this.trackDeviceService = trackDeviceService;
    }

    /* to find all the inactive devices
     **/
    @GetMapping("/inactiveDevices")
    public ResponseEntity<Page<TrackDevicePOJO>> findInactiveDevices() {
        LOGGER.info("TrackDevicePOJO inactive devices [pageable={0}");
        final Page<TrackDevicePOJO> result = trackDeviceService.findInactiveDevices();
        LOGGER.info("Found {} inactive devices !", result.getSize());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /* to find all the active devices
     **/
    @GetMapping("/activeDevices")
    public ResponseEntity<Page<TrackDevicePOJO>> findActiveDevices() {
        LOGGER.info("TrackDevicePOJO active devices [pageable={0}");
        final Page<TrackDevicePOJO> result = trackDeviceService.findActiveDevices();
        LOGGER.info("Found {} active devices !", result.getSize());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "{trackDeviceId}/{deviceStatusEnum}", consumes = "application/json")
    public ResponseEntity<TrackDevicePOJO> update(@PathVariable("trackDeviceId") Long trackDeviceId, @PathVariable DeviceStatusEnum deviceStatusEnum) {
        LOGGER.info("TrackDevice update request [trackDeviceId={1} | status={}}", resolveIdForLogger(trackDeviceId, deviceStatusEnum));
        TrackDevicePOJO updated = trackDeviceService.updateDeviceStatusConfig(trackDeviceId, deviceStatusEnum);
        if (updated != null) {
            if (updated.getErrorMessage() == null) {
                LOGGER.info("TrackDevice successfully updated");
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                LOGGER.info("Conflict occurs");
                return new ResponseEntity<>(updated, HttpStatus.CONFLICT);
            }
        } else {
            LOGGER.info(NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{trackDeviceId}")
    public ResponseEntity<Void> delete(@PathVariable("trackDeviceId") Long trackDeviceId) {
        LOGGER.info("TrackDevice  deletion request : {}", trackDeviceId);
        if (trackDeviceService.delete(trackDeviceId)) {
            LOGGER.info("TrackDevice successfully deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            LOGGER.info(NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private String resolveIdForLogger(Long trackDeviceId, DeviceStatusEnum deviceStatusEnum) {
        return trackDeviceId +
                "/" +
                deviceStatusEnum;
    }

}
