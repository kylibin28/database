package database.Database.service;

import database.Database.entity.Rate;
import database.Database.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    public Rate save(Rate rate) {
        return rateRepository.save(rate);
    }

    public void delete(Rate rate) {
        rateRepository.delete(rate);
    }

    public Rate findById(Integer id) {
        return rateRepository.findOne(id);
    }

}
