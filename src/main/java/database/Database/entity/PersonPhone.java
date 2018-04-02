package database.Database.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "personPhone")
@IdClass(PersonPhone.class)
public class PersonPhone implements Serializable {

    @Id
    private Integer addressId;

    @Id
    private Integer pasportNumber;

    @Id
    private Integer phoneNumber;

    public PersonPhone(Integer addressId, Integer pasportNumber, Integer phoneNumber) {
        this.addressId = addressId;
        this.pasportNumber = pasportNumber;
        this.phoneNumber = phoneNumber;
    }

    public PersonPhone() {
    }

    public Integer getAddressId() {
        return addressId;
    }

    public Integer getPasportNumber() {
        return pasportNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "PersonPhone{" +
                ", addressId=" + addressId +
                ", pasportNumber=" + pasportNumber +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
