package database.Database.service;

import database.Database.entity.District;
import database.Database.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository distinctRepository;

    public List<District> findAll() {
        return distinctRepository.findAll();
    }

    public District save(District distinct) {
        return distinctRepository.save(distinct);
    }

    public void delete(District distinct) {
        distinctRepository.delete(distinct);
    }

    public District findById(String id) {
        return distinctRepository.findOne(id);
    }

}
