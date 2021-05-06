package com.example.iot.persistence.dao.ifc;

import com.example.iot.domain.entity.TrackDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TrackDeviceDao {
    Page<TrackDevice> findInactiveDevices(Pageable pageable);

    Optional<TrackDevice> get(Long trackDeviceId);


    TrackDevice save(TrackDevice trackDevice);

    boolean delete(Long trackDeviceId);

    Page<TrackDevice> findActiveDevices(Pageable pageable);
}
