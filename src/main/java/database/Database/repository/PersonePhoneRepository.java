package database.Database.repository;

import database.Database.entity.PersonPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonePhoneRepository  extends JpaRepository<PersonPhone, Integer>{
}
