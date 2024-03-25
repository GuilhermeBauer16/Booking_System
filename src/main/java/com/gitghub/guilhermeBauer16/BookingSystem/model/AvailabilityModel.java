package com.gitghub.guilhermeBauer16.BookingSystem.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AvailabilityModel {

    private UUID id;
    private ServicesModel servicesModel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer availableCapacity;

    public AvailabilityModel() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailabilityModel that = (AvailabilityModel) o;
        return Objects.equals(id, that.id) && Objects.equals(servicesModel, that.servicesModel) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(availableCapacity, that.availableCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicesModel, startTime, endTime, availableCapacity);
    }
}
