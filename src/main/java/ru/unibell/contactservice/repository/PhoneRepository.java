package ru.unibell.contactservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.unibell.contactservice.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
