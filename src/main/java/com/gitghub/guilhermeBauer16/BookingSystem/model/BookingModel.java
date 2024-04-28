package com.gitghub.guilhermeBauer16.BookingSystem.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "bookings")
public class BookingModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "service_id " )

    private ServicesModel servicesModel;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer availableCapacity;

    private String additionalInfo;

    @Column(name = "is_available")
    private Boolean available;

    public BookingModel() {
        this.available = false;
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

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingModel that = (BookingModel) o;
        return Objects.equals(id, that.id) && Objects.equals(servicesModel, that.servicesModel) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(availableCapacity, that.availableCapacity) && Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicesModel, startTime, endTime, availableCapacity, additionalInfo, available);
    }
}
