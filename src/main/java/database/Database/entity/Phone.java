package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Column(name = "isConnect", nullable = false)
    private Boolean isConnect;

    @ManyToOne
    @JoinColumn(name = "atsId")
    private ATS atsId;

    @OneToMany(mappedBy = "phoneNumber")
    private Set<OrganisationPhone> organisationPhone = new HashSet<OrganisationPhone>();

    @OneToMany(mappedBy = "phoneNumber")
    private Set<PersonPhone> personPhone = new HashSet<PersonPhone>();

    public Phone(Integer phoneNumber, Boolean isConnect,ATS atsId) {
        this.phoneNumber = phoneNumber;
        this.isConnect = isConnect;
        this.atsId = atsId;
    }

    public Phone() {
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getConnect() {
        return isConnect;
    }

    public ATS getAtsId() {
        return atsId;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setConnect(Boolean connect) {
        isConnect = connect;
    }

    public void setAtsId(ATS atsId) {
        this.atsId = atsId;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber=" + phoneNumber +
                ", isConnect=" + isConnect +
                ", atsId=" + atsId +
                '}';
    }
}
