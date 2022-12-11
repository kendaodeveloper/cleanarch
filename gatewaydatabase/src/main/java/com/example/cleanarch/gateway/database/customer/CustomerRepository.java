package com.example.cleanarch.gateway.database.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerTable, UUID> {
  Optional<CustomerTable> findOneById(UUID id);

  Optional<CustomerTable> findOneByEmail(String email);

  Optional<CustomerTable> findOneByCpf(String cpf);

  Page<CustomerTable> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

  Page<CustomerTable> findAllByBirthDate(LocalDate birthDate, Pageable pageable);
}