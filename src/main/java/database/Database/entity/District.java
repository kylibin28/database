package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "district")
public class District {

    @Id
    @Column(name = "distinctName", length = 100)
    private String distinctName;

    @OneToMany(mappedBy = "distinctName")
    private Set<Address> address = new HashSet<Address>();

    public District(String distinctName) {
        this.distinctName = distinctName;
    }

    public District() {
    }

    public String getDistinctName() {
        return distinctName;
    }

    public Set<Address> getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "District{" +
                "distinctName='" + distinctName + '\'' +
                '}';
    }
}
