package com.spring.security.jwt.repository;

import com.spring.security.jwt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAllByOrderByProductNameAsc();

    //public List<Product> findAllByOrderByProductNameDesc();

    public List<Product> findByProductNameContainsAllIgnoreCase(String name);

    /* @Query("SELECT p FROM Product p WHERE p.productCode IN :productCodes")
     List<Product> findByProductCodes(@Param("productCodes") List<Integer> productCodes);*/

    public List<Product> findByProductCodeIn(List<Integer> collect);

    public List<Product> findByProductCodeInAndQuantityInStockGreaterThan(List<Integer> productCodes, int quantityInStock);

}
