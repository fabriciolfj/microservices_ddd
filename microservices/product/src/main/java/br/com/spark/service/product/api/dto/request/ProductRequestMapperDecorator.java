package br.com.spark.service.product.api.dto.request;

import br.com.spark.service.product.domain.model.Product;
import br.com.spark.service.product.domain.model.enuns.ProductStatus;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class ProductRequestMapperDecorator implements ProductRequestMapper {

    @Autowired
    private ProductRequestMapper mapper;

    @Override
    public Product toDomain(ProductRequestDto dto) {
        var product = mapper.toDomain(dto);
        product.setStatus(ProductStatus.toEnum(dto.getStatus()));
        return product;
    }
}
