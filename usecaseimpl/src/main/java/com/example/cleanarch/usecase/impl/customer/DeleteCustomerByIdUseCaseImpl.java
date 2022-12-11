package com.example.cleanarch.usecase.impl.customer;

import com.example.cleanarch.gateway.base.customer.DeleteCustomerByIdGateway;
import com.example.cleanarch.usecase.base.customer.DeleteCustomerByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteCustomerByIdUseCaseImpl implements DeleteCustomerByIdUseCase {
  private final DeleteCustomerByIdGateway deleteCustomerByIdGateway;

  @Override
  @Transactional
  public void execute(UUID id) {
    this.deleteCustomerByIdGateway.deleteOneById(id);
  }
}
