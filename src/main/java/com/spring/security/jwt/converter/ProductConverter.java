package com.spring.security.jwt.converter;



import com.spring.security.jwt.dto.ProductDto;
import com.spring.security.jwt.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        //dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setProductDescription(product.getProductDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantityInStock(product.getQuantityInStock());
        return dto;
    }

    public List<ProductDto> entityToDto(List<Product> products) {
        return products.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public Product dtoToEntity(ProductDto dto) {
        Product product = new Product();
        //product.setProductId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityInStock(dto.getQuantityInStock());
        return product;
    }

    public List<Product> dtoToEntity(List<ProductDto> dtos) {
        return dtos.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}