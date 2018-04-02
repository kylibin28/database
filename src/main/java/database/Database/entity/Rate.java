package database.Database.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rateId")
    private Integer rateId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "value", nullable = false)
    private Integer value;

    @OneToMany(mappedBy = "rateId")
    private Set<Payment> payment = new HashSet<Payment>();

    public Rate(Date date, Integer value) {
        this.date = date;
        this.value = value;
    }

    public Rate() {
    }

    public Integer getRateId() {
        return rateId;
    }

    public Date getDate() {
        return date;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
