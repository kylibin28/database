package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "district")
public class District {

    @Id
    @Column(name = "districtName", length = 100)
    private String districtName;

    @OneToMany(mappedBy = "districtName")
    private Set<Address> address = new HashSet<Address>();

    public District(String districtName) {
        this.districtName = districtName;
    }

    public District() {
    }

    public String getDistrictName() {
        return districtName;
    }

    public Set<Address> getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtName='" + districtName + '\'' +
                '}';
    }
}
