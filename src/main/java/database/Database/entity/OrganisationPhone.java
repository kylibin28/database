package database.Database.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "organisationPhone")
@IdClass(OrganisationPhone.class)
public class OrganisationPhone implements Serializable{

    @Id
    private Integer addressId;

    @Id
    private Integer registrationNumber;

    @Id
    private Integer phoneNumber;

    public OrganisationPhone(Integer addressId, Integer registrationNumber, Integer phoneNumber) {
        this.addressId = addressId;
        this.registrationNumber = registrationNumber;
        this.phoneNumber = phoneNumber;
    }

    public OrganisationPhone() {
    }

    public Integer getAddressId() {
        return addressId;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "OrganisationPhone{" +
                ", addressId=" + addressId +
                ", registrationNumber=" + registrationNumber +
                '}';
    }
}
