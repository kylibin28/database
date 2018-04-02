package database.Database.service;

import database.Database.entity.OrganisationPhone;
import database.Database.repository.OrganisationPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationPhoneService {

    @Autowired
    private OrganisationPhoneRepository organisationPhoneRepository;

    public List<OrganisationPhone> findAll() {
        return organisationPhoneRepository.findAll();
    }

    public OrganisationPhone save(OrganisationPhone organisationPhone) {
        return organisationPhoneRepository.save(organisationPhone);
    }

    public void delete(OrganisationPhone organisationPhone) {
        organisationPhoneRepository.delete(organisationPhone);
    }

    public OrganisationPhone findById(Integer id) {
        return organisationPhoneRepository.findOne(id);
    }

}
