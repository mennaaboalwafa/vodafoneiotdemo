package com.example.iot.persistence.dao.impl;

import com.example.iot.domain.entity.TrackDevice;
import com.example.iot.persistence.dao.ifc.TrackDeviceDao;
import com.example.iot.persistence.repository.TrackDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrackDeviceDaoImpl implements TrackDeviceDao {
    private final TrackDeviceRepository trackDeviceRepository;

    @Autowired
    public TrackDeviceDaoImpl(TrackDeviceRepository trackDeviceRepository) {
        this.trackDeviceRepository = trackDeviceRepository;
    }

    // find all track devices with status equal 0 (inactive)
    @Override
    public Page<TrackDevice> findInactiveDevices(Pageable pageable) {
        return trackDeviceRepository.findAllByTrackDeviceStatusEquals(pageable, 0);
    }

    @Override
    public Optional<TrackDevice> get(Long trackDeviceId) {
        return trackDeviceRepository.findById(trackDeviceId);
    }

    @Override
    public TrackDevice save(TrackDevice trackDevice) {
        return trackDeviceRepository.save(trackDevice);
    }

    @Override
    public boolean delete(Long trackDeviceId) {
        if (get(trackDeviceId).isPresent()) {
            trackDeviceRepository.deleteById(trackDeviceId);
            return true;
        }
        return false;
    }

    @Override
    public Page<TrackDevice> findActiveDevices(Pageable pageable) {
        return trackDeviceRepository.findAllByTrackDeviceStatusEqualsAndSimByTrackDeviceId_SimStatusAndTrackDeviceTempBetweenOrderByTrackDeviceId(
                pageable, 1, 1, -25, 85);
    }
}
