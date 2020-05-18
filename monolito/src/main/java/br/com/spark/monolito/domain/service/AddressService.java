package br.com.spark.monolito.domain.service;

import br.com.spark.monolito.domain.dto.AddressDto;
import br.com.spark.monolito.domain.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService {

    public static AddressDto mapToDto(final Address address) {
        return Optional.ofNullable(address)
                .map(ad -> new AddressDto(ad.getAddress1(),
                            ad.getAddress2(),
                            ad.getCity(),
                            ad.getPostCode(),
                            ad.getCountry()))
                .orElse(null);

    }
}
