package com.gitghub.guilhermeBauer16.BookingSystem.vo.V1.security;

import com.gitghub.guilhermeBauer16.BookingSystem.model.security.AddressModel;
import com.gitghub.guilhermeBauer16.BookingSystem.model.security.UserPatternsModel;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class UserVO {

    private UUID id;
    private String fullName;
    private String gender;
    private Date dateOfBirth;
    private UserPatternsModel userPatternsModel;
    private AddressModel addressModel;
    private Boolean active;

    public UserVO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserPatternsModel getUserPatternsModel() {
        return userPatternsModel;
    }

    public void setUserPatternsModel(UserPatternsModel userPatternsModel) {
        this.userPatternsModel = userPatternsModel;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
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
        UserVO userVO = (UserVO) o;
        return Objects.equals(id, userVO.id) && Objects.equals(fullName, userVO.fullName) && Objects.equals(gender, userVO.gender) && Objects.equals(dateOfBirth, userVO.dateOfBirth) && Objects.equals(userPatternsModel, userVO.userPatternsModel) && Objects.equals(addressModel, userVO.addressModel) && Objects.equals(active, userVO.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, gender, dateOfBirth, userPatternsModel, addressModel, active);
    }
}
