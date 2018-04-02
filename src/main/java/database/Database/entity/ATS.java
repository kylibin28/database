package database.Database.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ats")
public class ATS {

    @Id
    @Column(name = "atsId")
    private Integer atsId;

    @OneToMany(mappedBy = "atsId")
    private Set<Phone> ats = new HashSet<Phone>();

    public ATS(Integer atsId) {
        this.atsId = atsId;
    }

    public ATS() {
    }

    public int getAtsId() {
        return atsId;
    }

    public Set<Phone> getAts() {
        return ats;
    }

    @Override
    public String toString() {
        return "ATS{" +
                "atsId=" + atsId +
                '}';
    }
}
