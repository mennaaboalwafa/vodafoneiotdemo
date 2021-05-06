package com.example.iot.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRACK_DEVICE", schema = "IOT_SHOP")
public class TrackDevice {
    private long trackDeviceId;
    private long trackDeviceStatus;
    private long trackDeviceTemp;
    private Sim simByTrackDeviceId;

    public TrackDevice() {
    }

    public TrackDevice(long trackDeviceId, long trackDeviceStatus, long trackDeviceTemp, Sim sim) {
    }

    @Id
    @GeneratedValue(generator = "TRACK_DEVICE_SEQ")
    @Column(name = "TRACK_DEVICE_ID")
    public long getTrackDeviceId() {
        return trackDeviceId;
    }

    public void setTrackDeviceId(Long trackDeviceId) {
        this.trackDeviceId = trackDeviceId;
    }


    @Basic
    @Column(name = "TRACK_DEVICE_STATUS")
    public long getTrackDeviceStatus() {
        return trackDeviceStatus;
    }

    public void setTrackDeviceStatus(long trackDeviceStatus) {
        this.trackDeviceStatus = trackDeviceStatus;
    }

    @Basic
    @Column(name = "TRACK_DEVICE_TEMP")
    public long getTrackDeviceTemp() {
        return trackDeviceTemp;
    }

    public void setTrackDeviceTemp(long trackDeviceTemp) {
        this.trackDeviceTemp = trackDeviceTemp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackDevice that = (TrackDevice) o;

        if (trackDeviceId != that.trackDeviceId) return false;
        if (trackDeviceStatus != that.trackDeviceStatus) return false;
        return trackDeviceTemp == that.trackDeviceTemp;
    }

    @Override
    public int hashCode() {
        int result = (int) (trackDeviceId ^ (trackDeviceId >>> 32));
        result = 31 * result + (int) (trackDeviceTemp ^ (trackDeviceTemp >>> 32));
        return result;
    }


    @OneToOne
    @JoinColumn(name = "TRACK_DEVICE_ID", referencedColumnName = "SIM_ID", nullable = false)
    public Sim getSimByTrackDeviceId() {
        return simByTrackDeviceId;
    }

    public void setSimByTrackDeviceId(Sim simByTrackDeviceId) {
        this.simByTrackDeviceId = simByTrackDeviceId;
    }
}
