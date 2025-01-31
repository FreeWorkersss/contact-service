package ru.unibell.contactservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.unibell.contactservice.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
