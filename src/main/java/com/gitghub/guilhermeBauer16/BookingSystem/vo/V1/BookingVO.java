package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1;

import com.gitghub.guilhermeBauer16.BookingSystem.model.ServicesModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class BookingVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private ServicesModel servicesModel;
    private LocalDateTime dateTime;
    private String status;
    private String additionalInfo;

    private Boolean isAvailable;

    public BookingVO() {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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
        BookingVO that = (BookingVO) o;
        return Objects.equals(id, that.id) && Objects.equals(servicesModel, that.servicesModel) && Objects.equals(dateTime, that.dateTime) && Objects.equals(status, that.status) && Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(isAvailable, that.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, servicesModel, dateTime, status, additionalInfo, isAvailable);
    }
}
