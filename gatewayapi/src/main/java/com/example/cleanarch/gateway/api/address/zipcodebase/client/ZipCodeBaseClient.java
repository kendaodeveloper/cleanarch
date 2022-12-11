package com.example.cleanarch.gateway.api.address.zipcodebase.client;

import com.example.cleanarch.gateway.api.address.zipcodebase.response.ZipCodeBaseAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ZipCodeBaseClient", url = "${feign.client.zip-code-base.url}")
public interface ZipCodeBaseClient {
  @GetMapping("/landing_demo?codes={postalCode}&country=US")
  Optional<ZipCodeBaseAddressResponse> getAddressByPostalCode(@PathVariable String postalCode);
}
