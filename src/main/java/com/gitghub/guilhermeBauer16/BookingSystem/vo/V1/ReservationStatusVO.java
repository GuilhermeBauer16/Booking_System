package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ReservationStatusVO extends RepresentationModel<ReservationStatusVO> implements Serializable {
    private UUID id;

    private String status;

    private Boolean active;

    public ReservationStatusVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {

        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservationStatusVO vo = (ReservationStatusVO) o;
        return Objects.equals(id, vo.id) && Objects.equals(status, vo.status) && Objects.equals(active, vo.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, status, active);
    }
}
