package database.Database;

import database.Database.entity.*;
import database.Database.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ATSService atsService;

    @Autowired
    private DistinctService distinctService;

    @Autowired
    private OrganisationPhoneService organisationPhoneService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PersonPhoneService personPhoneService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private RateService rateService;

    @Test
    public void add() {
        distinctService.save(new District("Железнодорожный"));
        rateService.save(new Rate(new Date(System.currentTimeMillis()), 190));
        atsService.save(new ATS(1));
        personService.save(new Person(432, "Аверкиев Максим Константинович", true));
        addressService.save(new Address("Самара", 443013, "Киевская", 10, distinctService.findById("Железнодорожный")));
        organisationService.save(new Organisation(123, "IT-universe"));
        phoneService.save(new Phone(3409032, true, atsService.findById(1)));
        organisationPhoneService.save(new OrganisationPhone(2, 123, 3409032));
        personPhoneService.save(new PersonPhone(2, 432, 3409032));
        paymentService.save(new Payment(2010, 9, phoneService.findById(3409032), rateService.findById(1)));
    }

    @Test
    public void select() {

    }

    @Test
    public void selectAll() {
        distinctService.findAll().forEach(item -> System.out.println(item.toString()));
        rateService.findAll().forEach(item -> System.out.println(item.toString()));
        atsService.findAll().forEach(item -> System.out.println(item.toString()));
        personService.findAll().forEach(item -> System.out.println(item.toString()));
        addressService.findAll().forEach(item -> System.out.println(item.toString()));
        organisationService.findAll().forEach(item -> System.out.println(item.toString()));
        phoneService.findAll().forEach(item -> System.out.println(item.toString()));
        organisationPhoneService.findAll().forEach(item -> System.out.println(item.toString()));
        personPhoneService.findAll().forEach(item -> System.out.println(item.toString()));
        paymentService.findAll().forEach(item -> System.out.println(item.toString()));
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }
}
