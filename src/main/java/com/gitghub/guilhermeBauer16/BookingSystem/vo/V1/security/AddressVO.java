package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class AddressVO extends RepresentationModel<AddressVO> {

    private UUID id;
    private String street;
    private String neighborhood ;
    private String zipCode;
    private String number;
    private String city;
    private String complement;
    private Boolean active;

    public AddressVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
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
        AddressVO addressVO = (AddressVO) o;
        return Objects.equals(id, addressVO.id) && Objects.equals(street, addressVO.street) && Objects.equals(neighborhood, addressVO.neighborhood) && Objects.equals(zipCode, addressVO.zipCode) && Objects.equals(number, addressVO.number) && Objects.equals(city, addressVO.city) && Objects.equals(complement, addressVO.complement) && Objects.equals(active, addressVO.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, neighborhood, zipCode, number, city, complement, active);
    }
}
