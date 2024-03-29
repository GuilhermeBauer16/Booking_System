package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1;

import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AvailabilityVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private ServicesModel servicesModel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer availableCapacity;

    private Boolean isAvailable;

    public AvailabilityVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ServicesModel getServicesModel() {
        return servicesModel;
    }

    public void setServicesModel(ServicesModel servicesModel) {
        this.servicesModel = servicesModel;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityVO that = (AvailabilityVO) o;
        return Objects.equals(id, that.id) && Objects.equals(servicesModel, that.servicesModel) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(availableCapacity, that.availableCapacity) && Objects.equals(isAvailable, that.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicesModel, startTime, endTime, availableCapacity, isAvailable);
    }
}
