package com.gitghub.guilhermeBauer16.BookingSystem.model.security;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    private String gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name= "user_patterns_fk")
    private UserPatternsModel userPatternsModel;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressModel addressModel;
    private Boolean active;

    public UserModel() {
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
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(fullName, userModel.fullName) && Objects.equals(gender, userModel.gender) && Objects.equals(dateOfBirth, userModel.dateOfBirth) && Objects.equals(userPatternsModel, userModel.userPatternsModel) && Objects.equals(addressModel, userModel.addressModel) && Objects.equals(active, userModel.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, gender, dateOfBirth, userPatternsModel, addressModel, active);
    }
}
