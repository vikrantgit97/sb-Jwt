package in.vit.security.jwt.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class ProductDto implements Serializable {

    private Integer productCode;

    private String productName;

    private String productDescription;

    private Integer quantityInStock;

    private Double price;

}

