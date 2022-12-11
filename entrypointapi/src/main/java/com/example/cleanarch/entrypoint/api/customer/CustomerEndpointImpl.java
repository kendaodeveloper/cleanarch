package com.example.cleanarch.entrypoint.api.customer;

import com.example.cleanarch.entrypoint.api.customer.request.WriteCustomerEndpointRequest;
import com.example.cleanarch.entrypoint.api.customer.response.CustomerEndpointResponse;
import com.example.cleanarch.usecase.base.customer.*;
import com.example.cleanarch.usecase.base.customer.request.CustomerUseCaseRequest;
import com.example.cleanarch.usecase.base.customer.response.CustomerUseCaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/v1/customers")
@Tag(name = "Customer Endpoint", description = "/v1/customers")
@AllArgsConstructor
public class CustomerEndpointImpl {
  private final GetCustomerByIdUseCase getCustomerByIdUseCase;
  private final GetCustomersByFilterUseCase getCustomersByFilterUseCase;
  private final CreateCustomerUseCase createCustomerUseCase;
  private final UpdateCustomerUseCase updateCustomerUseCase;
  private final DeleteCustomerByIdUseCase deleteCustomerByIdUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Customer(s)")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid parameters"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public List<CustomerEndpointResponse> getByFilter(
      @RequestParam(required = false) @Parameter(name = "id", description = "id") UUID id,
      @RequestParam(required = false) @Parameter(name = "name", description = "name") String name,
      @RequestParam(required = false) @Parameter(name = "cpf", description = "cpf") String cpf,
      @RequestParam(required = false) @Parameter(name = "email", description = "email") String email,
      @RequestParam(required = false) @Parameter(name = "birthDate", description = "birth date") LocalDate birthDate,
      @RequestParam(required = false, defaultValue = "1") @Parameter(name = "offset", description = "page number") @Min(1) Integer offset,
      @RequestParam(required = false, defaultValue = "10") @Parameter(name = "limit", description = "page size") @Min(1) Integer limit
  ) {
    return this.getCustomersByFilterUseCase.execute(id, name, cpf, email, birthDate, offset, limit)
        .stream().map(this::mapUseCaseResponseToEndpointResponse).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid id"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "502", description = "Bad Gateway")
      }
  )
  public CustomerEndpointResponse getById(
      @PathVariable @Parameter(name = "id", description = "id") UUID id
  ) {
    return this.mapUseCaseResponseToEndpointResponse(
        this.getCustomerByIdUseCase.execute(id)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Created"),
          @ApiResponse(responseCode = "400", description = "Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "409", description = "CPF/Email already exists"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public CustomerEndpointResponse post(@RequestBody @Valid WriteCustomerEndpointRequest request) {
    return this.mapUseCaseResponseToEndpointResponse(
        this.createCustomerUseCase.execute(
            this.mapEndpointRequestToUseCaseRequest(request)
        )
    );
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Update Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid id | Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found"),
          @ApiResponse(responseCode = "409", description = "CPF/Email already exists"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public CustomerEndpointResponse put(
      @PathVariable @Parameter(name = "id", description = "id") UUID id,
      @RequestBody @Valid WriteCustomerEndpointRequest request
  ) {
    return this.mapUseCaseResponseToEndpointResponse(
        this.updateCustomerUseCase.execute(
            id,
            this.mapEndpointRequestToUseCaseRequest(request)
        )
    );
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "No Content"),
          @ApiResponse(responseCode = "400", description = "Invalid id"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public void delete(@PathVariable @Parameter(name = "id", description = "id") UUID id) {
    this.deleteCustomerByIdUseCase.execute(id);
  }

  private CustomerEndpointResponse mapUseCaseResponseToEndpointResponse(CustomerUseCaseResponse customerUseCase) {
    return new CustomerEndpointResponse(
        customerUseCase.getId(),
        customerUseCase.getName(),
        customerUseCase.getCpf(),
        customerUseCase.getEmail(),
        customerUseCase.getBirthDate()
    );
  }

  private CustomerUseCaseRequest mapEndpointRequestToUseCaseRequest(WriteCustomerEndpointRequest customerEndpoint) {
    return new CustomerUseCaseRequest(
        customerEndpoint.getName(),
        customerEndpoint.getCpf(),
        customerEndpoint.getEmail(),
        customerEndpoint.getBirthDate()
    );
  }
}
