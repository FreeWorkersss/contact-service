package ru.unibell.contactservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.unibell.contactservice.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}