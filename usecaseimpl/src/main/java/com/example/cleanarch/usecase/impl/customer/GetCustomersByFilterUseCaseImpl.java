package com.example.cleanarch.usecase.impl.customer;

import com.example.cleanarch.entity.exception.NotFoundException;
import com.example.cleanarch.gateway.base.customer.*;
import com.example.cleanarch.usecase.base.customer.GetCustomersByFilterUseCase;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import com.example.cleanarch.usecase.impl.customer.mapper.CustomerUseCaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetCustomersByFilterUseCaseImpl implements GetCustomersByFilterUseCase {
  private final GetCustomerByIdGateway getCustomerByIdGateway;
  private final GetCustomerByCpfGateway getCustomerByCpfGateway;
  private final GetCustomerByEmailGateway getCustomerByEmailGateway;
  private final GetCustomersByNameGateway getCustomersByNameGateway;
  private final GetCustomersByBirthDateGateway getCustomersByBirthDateGateway;
  private final GetAllCustomersGateway getAllCustomersGateway;

  @Override
  public List<CustomerUseCaseResponse> execute(
      UUID id, String name, String cpf, String email, LocalDate birthDate, Integer pageNumber, Integer pageSize
  ) {
    if (Objects.nonNull(id)) {
      return this.getById(id);
    } else if (Objects.nonNull(cpf)) {
      return this.getByCpf(cpf);
    } else if (Objects.nonNull(email)) {
      return this.getByEmail(email);
    } else if (Objects.nonNull(name)) {
      return this.getByName(name, pageNumber, pageSize);
    } else if (Objects.nonNull(birthDate)) {
      return this.getByBirthDate(birthDate, pageNumber, pageSize);
    } else {
      return this.getAll(pageNumber, pageSize);
    }
  }

  private List<CustomerUseCaseResponse> getById(UUID id) {
    try {
      return List.of(
          CustomerUseCaseMapper.mapGatewayToUseCase(this.getCustomerByIdGateway.getOneById(id))
      );
    } catch (NotFoundException e) {
      return List.of();
    }
  }

  private List<CustomerUseCaseResponse> getByCpf(String cpf) {
    try {
      return List.of(
          CustomerUseCaseMapper.mapGatewayToUseCase(this.getCustomerByCpfGateway.getOneByCpf(cpf))
      );
    } catch (NotFoundException e) {
      return List.of();
    }
  }

  private List<CustomerUseCaseResponse> getByEmail(String email) {
    try {
      return List.of(
          CustomerUseCaseMapper.mapGatewayToUseCase(this.getCustomerByEmailGateway.getOneByEmail(email))
      );
    } catch (NotFoundException e) {
      return List.of();
    }
  }

  private List<CustomerUseCaseResponse> getByName(String name, Integer pageNumber, Integer pageSize) {
    return this.getCustomersByNameGateway.getAllByName(name, pageNumber, pageSize)
        .stream().map(CustomerUseCaseMapper::mapGatewayToUseCase).collect(Collectors.toList());
  }

  private List<CustomerUseCaseResponse> getByBirthDate(LocalDate birthDate, Integer pageNumber, Integer pageSize) {
    return this.getCustomersByBirthDateGateway.getAllByBirthDate(birthDate, pageNumber, pageSize)
        .stream().map(CustomerUseCaseMapper::mapGatewayToUseCase).collect(Collectors.toList());
  }

  private List<CustomerUseCaseResponse> getAll(Integer pageNumber, Integer pageSize) {
    return this.getAllCustomersGateway.getAll(pageNumber, pageSize)
        .stream().map(CustomerUseCaseMapper::mapGatewayToUseCase).collect(Collectors.toList());
  }
}
