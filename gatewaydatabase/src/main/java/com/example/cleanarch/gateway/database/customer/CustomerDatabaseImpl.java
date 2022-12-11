package com.example.cleanarch.gateway.database.customer;

import com.example.cleanarch.gateway.base.customer.*;
import com.example.cleanarch.gateway.base.customer.dto.CustomerGatewayDto;
import com.example.cleanarch.gateway.base.customer.exception.CustomerAlreadyExistsException;
import com.example.cleanarch.gateway.base.customer.exception.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerDatabaseImpl implements
    GetCustomerByIdGateway,
    GetCustomerByCpfGateway,
    GetCustomerByEmailGateway,
    GetAllCustomersGateway,
    GetCustomersByNameGateway,
    GetCustomersByBirthDateGateway,
    CreateCustomerGateway,
    UpdateCustomerGateway,
    DeleteCustomerByIdGateway,
    DeleteAllCustomersGateway {
  private final CustomerRepository repository;

  @Override
  public CustomerGatewayDto getOneById(UUID id) throws CustomerNotFoundException {
    return this.repository.findOneById(id)
        .map(CustomerTable::toDto).orElseThrow(() -> {
          throw new CustomerNotFoundException();
        });
  }

  @Override
  public CustomerGatewayDto getOneByCpf(String cpf) throws CustomerNotFoundException {
    return this.repository.findOneByCpf(cpf)
        .map(CustomerTable::toDto).orElseThrow(() -> {
          throw new CustomerNotFoundException();
        });
  }

  @Override
  public CustomerGatewayDto getOneByEmail(String email) throws CustomerNotFoundException {
    return this.repository.findOneByEmail(email)
        .map(CustomerTable::toDto).orElseThrow(() -> {
          throw new CustomerNotFoundException();
        });
  }

  @Override
  public List<CustomerGatewayDto> getAllByName(String name, Integer pageNumber, Integer pageSize) {
    Page<CustomerTable> customers =
        this.repository.findAllByNameContainingIgnoreCase(
            name, PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"))
        );

    return customers.stream().map(CustomerTable::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CustomerGatewayDto> getAllByBirthDate(LocalDate birthDate, Integer pageNumber, Integer pageSize) {
    Page<CustomerTable> customers =
        this.repository.findAllByBirthDate(
            birthDate, PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"))
        );

    return customers.stream().map(CustomerTable::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CustomerGatewayDto> getAll(Integer pageNumber, Integer pageSize) {
    Page<CustomerTable> customers = this.repository.findAll(
        PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"))
    );

    return customers.stream().map(CustomerTable::toDto).collect(Collectors.toList());
  }

  @Override
  public CustomerGatewayDto create(CustomerGatewayDto request) throws CustomerAlreadyExistsException {
    this.repository.findOneByCpf(request.getCpf()).ifPresent(x -> {
      throw new CustomerAlreadyExistsException("cpf");
    });
    this.repository.findOneByEmail(request.getEmail()).ifPresent(x -> {
      throw new CustomerAlreadyExistsException("email");
    });

    return this.repository.saveAndFlush(
        CustomerTable.builder()
            .name(request.getName())
            .cpf(request.getCpf())
            .email(request.getEmail())
            .birthDate(request.getBirthDate())
            .build()
    ).toDto();
  }

  @Override
  public CustomerGatewayDto update(UUID id, CustomerGatewayDto request) throws CustomerNotFoundException, CustomerAlreadyExistsException {
    CustomerTable customer = this.repository.findOneById(id)
        .orElseThrow(() -> {
          throw new CustomerNotFoundException();
        });

    this.repository.findOneByCpf(request.getCpf())
        .ifPresent(x -> {
          if (!x.getId().equals(id)) {
            throw new CustomerAlreadyExistsException("cpf");
          }
        });

    this.repository.findOneByEmail(request.getEmail())
        .ifPresent(x -> {
          if (!x.getId().equals(id)) {
            throw new CustomerAlreadyExistsException("email");
          }
        });

    customer.setName(request.getName());
    customer.setCpf(request.getCpf());
    customer.setEmail(request.getEmail());
    customer.setBirthDate(request.getBirthDate());

    return this.repository.saveAndFlush(customer).toDto();
  }

  @Override
  public void deleteOneById(UUID id) throws CustomerNotFoundException {
    this.repository.findOneById(id)
        .ifPresentOrElse(this.repository::delete, () -> {
          throw new CustomerNotFoundException();
        });
  }

  @Override
  public void deleteAll() {
    this.repository.deleteAll();
  }
}
