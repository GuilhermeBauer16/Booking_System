package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1;

import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class BookingVO extends RepresentationModel<BookingVO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private ServicesModel servicesModel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer availableCapacity;
    private String additionalInfo;

    private Boolean available;

    public BookingVO() {
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
        BookingVO bookingVO = (BookingVO) o;
        return Objects.equals(id, bookingVO.id) && Objects.equals(servicesModel, bookingVO.servicesModel) && Objects.equals(startTime, bookingVO.startTime) && Objects.equals(endTime, bookingVO.endTime) && Objects.equals(availableCapacity, bookingVO.availableCapacity) && Objects.equals(additionalInfo, bookingVO.additionalInfo) && Objects.equals(available, bookingVO.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicesModel, startTime, endTime, availableCapacity, additionalInfo, available);
    }
}
