package database.Database.service;

import database.Database.entity.Organisation;
import database.Database.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    public List<Organisation> findAll() {
        return organisationRepository.findAll();
    }

    public Organisation save(Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    public void delete(Organisation organisation) {
        organisationRepository.delete(organisation);
    }

    public Organisation findById(Integer id) {
        return organisationRepository.findOne(id);
    }

}
