package database.Database.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymentId")
    private Integer paymentId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @ManyToOne
    @JoinColumn(name = "phoneNumber")
    private Phone phoneNumber;

    @ManyToOne
    @JoinColumn(name = "rateId")
    private Rate rateId;

    public Payment(Integer year, Integer month, Phone phoneNumber, Rate rateId) {
        this.year = year;
        this.month = month;
        this.phoneNumber = phoneNumber;
        this.rateId = rateId;
    }

    public Payment() {
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Phone getPhoneNumber() {
        return phoneNumber;
    }

    public Rate getRateId() {
        return rateId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", year=" + year +
                ", month=" + month +
                ", phoneNumber=" + phoneNumber +
                ", rateId=" + rateId +
                '}';
    }
}
