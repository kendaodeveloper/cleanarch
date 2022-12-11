package com.example.cleanarch.usecase.base.address.response;

import com.example.cleanarch.usecase.base.address.enumerable.AddressUseCaseCountry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressUseCaseResponse {
  private String postalCode;
  private String publicPlace;
  private String number;
  private String neighbourhood;
  private String complement;
  private String city;
  private String state;
  private AddressUseCaseCountry country;
}
