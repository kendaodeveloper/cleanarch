package com.example.cleanarch.gateway.base.address.response;

import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressGatewayResponse {
  private String postalCode;
  private String publicPlace;
  private String neighbourhood;
  private String complement;
  private String city;
  private String state;
  private AddressGatewayCountry country;
}
