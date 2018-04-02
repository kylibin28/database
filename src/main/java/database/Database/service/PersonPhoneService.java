package database.Database.service;

import database.Database.entity.PersonPhone;
import database.Database.repository.PersonePhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonPhoneService {

    @Autowired
    private PersonePhoneRepository personePhoneRepository;

    public List<PersonPhone> findAll() {
        return personePhoneRepository.findAll();
    }

    public PersonPhone save(PersonPhone personPhone) {
        return personePhoneRepository.save(personPhone);
    }

    public void delete(PersonPhone personPhone) {
        personePhoneRepository.delete(personPhone);
    }

    public PersonPhone findById(Integer id) {
        return personePhoneRepository.findOne(id);
    }

}
