package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "pasportNumber")
    private Integer pasportNumber;

    @Column(name = "FIO", nullable = false)
    private String FIO;

    @Column(name = "privilege", nullable = false)
    private Boolean privilege;

    @OneToMany(mappedBy = "pasportNumber")
    private Set<PersonPhone> personPhone = new HashSet<PersonPhone>();

    public Person(Integer pasportNumber, String FIO, Boolean privilege) {
        this.pasportNumber = pasportNumber;
        this.FIO = FIO;
        this.privilege = privilege;
    }

    public Person() {
    }

    public Integer getPasportNumber() {
        return pasportNumber;
    }

    public String getFIO() {
        return FIO;
    }

    public Boolean getPrivilege() {
        return privilege;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pasportNumber=" + pasportNumber +
                ", FIO='" + FIO + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}