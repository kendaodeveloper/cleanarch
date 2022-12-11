package com.example.cleanarch.gateway.api.address.viacep.client;

import com.example.cleanarch.gateway.api.address.viacep.response.ViaCepAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ViaCepClient", url = "${feign.client.via-cep.url}")
public interface ViaCepClient {
  @GetMapping("/ws/{postalCode}/json")
  Optional<ViaCepAddressResponse> getAddressByPostalCode(@PathVariable String postalCode);
}
