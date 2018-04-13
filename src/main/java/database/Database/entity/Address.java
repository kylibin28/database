package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "addressId")
    private Integer addressId;

    @Column(name = "sity", nullable = false)
    private String sity;

    @Column(name = "index")
    private Integer index;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "buildingNumber", nullable = false)
    private Integer buildingNumber;

    @ManyToOne
    @JoinColumn(name = "districtName")
    private District districtName;

    @OneToMany(mappedBy = "addressId")
    private Set<OrganisationPhone> organisationPhone = new HashSet<OrganisationPhone>();

    @OneToMany(mappedBy = "addressId")
    private Set<PersonPhone> personPhone = new HashSet<PersonPhone>();

    public Address(String sity, Integer index, String street, Integer buildingNumber, District districtName) {
        this.sity = sity;
        this.index = index;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.districtName = districtName;
    }

    public Address() {
    }

    public Integer getAddressId() {
        return addressId;
    }

    public String getSity() {
        return sity;
    }

    public Integer getIndex() {
        return index;
    }

    public String getStreet() {
        return street;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public District getDistrictName() {
        return districtName;
    }


    public void setSity(String sity) {
        this.sity = sity;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setDistrictName(District districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", sity='" + sity + '\'' +
                ", index=" + index +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", districtName=" + districtName +
                '}';
    }
}
