package com.example.cleanarch.usecase.base.address;

import com.example.cleanarch.usecase.base.address.enumerable.AddressUseCaseCountry;
import com.example.cleanarch.usecase.base.address.response.AddressUseCaseResponse;

public interface GetAddressByPostalCodeUseCase {
  AddressUseCaseResponse getAddressByPostalCode(String postalCode, AddressUseCaseCountry country);
}
