package database.Database.service;

import database.Database.entity.ATS;
import database.Database.repository.ATSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATSService {

    @Autowired
    private ATSRepository atsRepository;

    public List<ATS> findAll() {
        return atsRepository.findAll();
    }

    public ATS save(ATS ats) {
        return atsRepository.save(ats);
    }

    public void delete(ATS ats) {
        atsRepository.delete(ats);
    }

    public ATS findById(Integer id) {
        return atsRepository.findOne(id);
    }

}
