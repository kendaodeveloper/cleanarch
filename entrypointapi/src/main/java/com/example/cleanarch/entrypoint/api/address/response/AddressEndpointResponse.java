package com.example.cleanarch.entrypoint.api.address.response;

import com.example.cleanarch.entrypoint.api.address.enumerable.AddressEndpointCountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEndpointResponse {
  private String postalCode;
  private String publicPlace;
  private String number;
  private String neighbourhood;
  private String complement;
  private String city;
  private String state;
  private AddressEndpointCountry country;
}
