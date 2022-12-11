package com.example.cleanarch.gateway.api.address.zipcodebase.response;

import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZipCodeBaseAddressResponse {
  private Object results;

  public boolean foundAddress() {
    try {
      return (this.results != null && !new ObjectMapper().writeValueAsString(this.results).equals("[]"));
    } catch (JsonProcessingException e) {
      return false;
    }
  }

  private ZipCodeBaseAddressResponseResultValue getFirstAddress(String postalCode) {
    try {
      final var resultList = (LinkedHashMap<?, ?>) this.results;
      final var resultListByPostalCode = (List<?>) resultList.get(postalCode);

      final var objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      return objectMapper.readValue(
          objectMapper.writeValueAsString(resultListByPostalCode.get(0)),
          ZipCodeBaseAddressResponseResultValue.class
      );
    } catch (Exception e) {
      return null;
    }
  }

  public AddressGatewayResponse toGatewayResponse(String postalCode) {
    final var address = this.getFirstAddress(postalCode);

    return new AddressGatewayResponse(
        address.getPostalCode(),
        address.getProvince(),
        null,
        null,
        address.getCity(),
        address.getState(),
        address.getCountryCode()
    );
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class ZipCodeBaseAddressResponseResultValue {
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("country_code")
    private AddressGatewayCountry countryCode;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("state_code")
    private String state_code;
    @JsonProperty("province")
    private String province;
    @JsonProperty("province_code")
    private String provinceCode;
  }
}
