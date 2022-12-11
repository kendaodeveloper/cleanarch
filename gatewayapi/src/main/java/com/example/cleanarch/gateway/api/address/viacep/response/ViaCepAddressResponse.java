package com.example.cleanarch.gateway.api.address.viacep.response;

import com.example.cleanarch.gateway.base.address.enumerable.AddressGatewayCountry;
import com.example.cleanarch.gateway.base.address.response.AddressGatewayResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViaCepAddressResponse {
  @JsonProperty("cep")
  private String postalCode;
  @JsonProperty("logradouro")
  private String publicPlace;
  @JsonProperty("bairro")
  private String neighbourhood;
  @JsonProperty("complemento")
  private String complement;
  @JsonProperty("localidade")
  private String city;
  @JsonProperty("uf")
  private String state;

  @JsonProperty("erro")
  private Boolean error;

  public boolean containsError() {
    return (error != null && error);
  }

  public AddressGatewayResponse toGatewayResponse() {
    return new AddressGatewayResponse(
        this.postalCode,
        this.publicPlace,
        this.neighbourhood,
        this.complement,
        this.city,
        this.state,
        AddressGatewayCountry.BR
    );
  }
}
