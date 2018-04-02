package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organisation")
public class Organisation {

    @Id
    @Column(name = "registrationNumber")
    private Integer registrationNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "registrationNumber")
    private Set<OrganisationPhone> organisationPhone = new HashSet<OrganisationPhone>();

    public Organisation(Integer registrationNumber, String name) {
        this.registrationNumber = registrationNumber;
        this.name = name;
    }

    public Organisation() {
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "registrationNumber=" + registrationNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
