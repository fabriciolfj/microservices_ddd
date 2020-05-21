package br.com.spark.service.product.api.dto.request;

import br.com.spark.service.product.domain.model.Product;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class ProductRequestMapperDecorator implements ProductRequestMapper {

    @Autowired
    private ProductRequestMapper mapper;

    @Override
    public Product toDomain(ProductRequestDto dto) {
        var product = mapper.toDomain(dto);
        return null;
    }
}
