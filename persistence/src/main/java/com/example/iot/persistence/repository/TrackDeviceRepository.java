package com.example.iot.persistence.repository;

import com.example.iot.domain.entity.TrackDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackDeviceRepository extends JpaRepository<TrackDevice, Long> {

    Page<TrackDevice> findAllByTrackDeviceStatusEquals(Pageable pageable,long status);
    Page<TrackDevice> findAllByTrackDeviceStatusEqualsAndSimByTrackDeviceId_SimStatusAndTrackDeviceTempBetweenOrderByTrackDeviceId(Pageable pageable,long deviceStatus,
                                                                                                               long simStatus, long value1, long value2);
}
