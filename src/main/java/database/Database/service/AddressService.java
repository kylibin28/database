package database.Database.service;

import database.Database.entity.Address;
import database.Database.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void delete(Address address) {
        addressRepository.delete(address);
    }

    public Address findById(Integer id) {
        return addressRepository.findOne(id);
    }

}
