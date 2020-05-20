package br.com.spark.service.order.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {

    private String address1;
    private String address2;
    private String city;
    private String postcode;
    private String country;

}
