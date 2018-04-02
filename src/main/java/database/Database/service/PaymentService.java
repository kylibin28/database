package database.Database.service;

import database.Database.entity.Payment;
import database.Database.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }

    public Payment findById(Integer id) {
        return paymentRepository.findOne(id);
    }

}
