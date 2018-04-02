package database.Database.service;

import database.Database.entity.Phone;
import database.Database.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    public void delete(Phone phone) {
        phoneRepository.delete(phone);
    }

    public Phone findById(Integer id) {
        return phoneRepository.findOne(id);
    }

}
