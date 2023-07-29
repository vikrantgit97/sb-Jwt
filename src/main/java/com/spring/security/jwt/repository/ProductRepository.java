package com.spring.security.jwt.repository;

import com.spring.security.jwt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByOrderByProductNameAsc();

    // List<Product> findAllByOrderByProductNameDesc();

    List<Product> findByProductNameContainsAllIgnoreCase(String name);

    /* @Query("SELECT p FROM Product p WHERE p.productCode IN :productCodes")
     List<Product> findByProductCodes(@Param("productCodes") List<Integer> productCodes);*/

    List<Product> findByProductCodeIn(List<Integer> collect);

    List<Product> findByProductCodeInAndQuantityInStockGreaterThan(List<Integer> productCodes, int quantityInStock);

}
