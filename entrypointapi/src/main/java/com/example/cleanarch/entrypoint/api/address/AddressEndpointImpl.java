package com.example.cleanarch.entrypoint.api.address;

import com.example.cleanarch.entrypoint.api.address.enumerable.AddressEndpointCountry;
import com.example.cleanarch.entrypoint.api.address.mapper.AddressEndpointMapper;
import com.example.cleanarch.entrypoint.api.address.response.AddressEndpointResponse;
import com.example.cleanarch.usecase.base.address.GetAddressByPostalCodeUseCase;
import com.example.cleanarch.usecase.base.address.enumerable.AddressUseCaseCountry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/addresses")
@Tag(name = "Address Endpoint", description = "/addresses")
@AllArgsConstructor
public class AddressEndpointImpl {
  private GetAddressByPostalCodeUseCase getAddressByPostalCodeUseCase;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Address By Postal Code")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid postal code"),
          // @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Address not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "502", description = "Bad Gateway")
      }
  )
  public AddressEndpointResponse getAddressByPostalCode(
      @RequestParam @Parameter(name = "postalCode", description = "postal code") String postalCode,
      @RequestParam @Parameter(name = "country", description = "country") AddressEndpointCountry country
  ) {
    return AddressEndpointMapper.mapUseCaseToEndpoint(
        this.getAddressByPostalCodeUseCase.getAddressByPostalCode(postalCode, AddressUseCaseCountry.valueOf(country.toString()))
    );
  }
}