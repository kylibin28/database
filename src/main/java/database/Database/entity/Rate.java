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

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;


    @Column(name = "value", nullable = false)
    private Integer value;

    @OneToMany(mappedBy = "rateId")
    private Set<Payment> payment = new HashSet<Payment>();

    public Rate(Integer year, Integer month,  Integer value) {
        this.month = month;
        this.year = year;
        this.value = value;
    }

    public Rate() {
    }

    public Integer getRateId() {
        return rateId;
    }


    public Integer getValue() {
        return value;
    }


    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getYear() {

        return year;
    }

    public Integer getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rateId=" + rateId +
                ", year=" + year +
                ", month=" + month +
                ", value=" + value +
                '}';
    }

}
