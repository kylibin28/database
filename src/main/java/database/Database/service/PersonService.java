package database.Database.service;

import database.Database.entity.Person;
import database.Database.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void delete(Person address) {
        personRepository.delete(address);
    }

    public Person findById(Integer id) {
        return personRepository.findOne(id);
    }

}
