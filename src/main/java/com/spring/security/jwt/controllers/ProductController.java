package com.spring.security.jwt.controllers;

import com.spring.security.jwt.dto.ProductDto;
import com.spring.security.jwt.models.Product;
import com.spring.security.jwt.serviceimpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    private final ModelMapper modelMapper;


    //add one product to table
    @PostMapping
    public ResponseEntity<Object> addOneProduct(@Valid @RequestBody final Product product) {
        Product product1 = productService.createProduct(product);
        return ResponseEntity.ok(product1);

    }


    //add list of product to table
    @PostMapping(value = "/list", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createMultipleProduct(@Valid @RequestBody final List<Product> product) {
        List<Product> productList = productService.createProductList(product);
        return ResponseEntity.ok(productList);
    }


    //modify one product by id
    @PutMapping(value = "{productCode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateOneProduct(@PathVariable final Integer productCode, @Valid @RequestBody final Product product) {
        Product product1 = productService.updateProduct(productCode, product);
        return ResponseEntity.ok(product1);
    }


    //partial update by id
    @PatchMapping(value = "{productCode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> partialUpdate(@PathVariable final Integer productCode, @Valid @RequestBody final Map<String, Object> update) {
        Product product = productService.partialUpdate(productCode, update);
        return ResponseEntity.ok(product);

    }


    //delete product by using id
    @DeleteMapping(value = "{productCode}")
    public ResponseEntity<Object> deleteOneProduct(@PathVariable final Integer productCode) {
        String product = productService.deleteProductById(productCode);
        return ResponseEntity.ok(product);
    }


    //delete all products
    @DeleteMapping
    public ResponseEntity<Object> deleteAllProduct() {
        String products = productService.deleteAllProducts();
        return ResponseEntity.ok(products);
    }


    //fetch product by id
    @GetMapping(value = "{productCode}")
    public ResponseEntity<Object> getOneProduct(@PathVariable final Integer productCode) {
        Product oneProduct = productService.getProductById(productCode);
        return ResponseEntity.ok(oneProduct);
    }


    //fetch all products
    @GetMapping
    public ResponseEntity<Object> getAllProduct() {
        List<ProductDto> products = productService.getAllProducts().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }


    //sort products according to ascending order
    @GetMapping("sort")
    public ResponseEntity<Object> getAllProductSorted() {
        List<ProductDto> products = productService.getAllSortedByProduct().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }


    //search product by productName
    @GetMapping("search/{productName}")
    public ResponseEntity<Object> searchByproduct(@PathVariable final String productName) {
        List<Product> products = productService.searchByProductName(productName);
        return ResponseEntity.ok(products);
    }


    //update price of product (increase/decrease)
    @PostMapping("{productCode}/price/{value}/{amount}")
    public ResponseEntity<Object> updatePrice(@PathVariable final Integer productCode, @PathVariable final String value, @PathVariable final Double amount) {
        Product products = productService.incrementDecrementProductPrice(productCode, value, amount);
        return ResponseEntity.ok(products);
    }


    //update quantity of product (increase/decrease)
    @PostMapping("{productCode}/quantity/{value}/{quantity}")
    public ResponseEntity<Object> updateQuantityInStock(@PathVariable final Integer productCode, @PathVariable final String value, @PathVariable final Integer quantity) {
        Product products = productService.incrementDecrementProductQuantityInStock(productCode, value, quantity);
        return ResponseEntity.ok(products);
    }


    @PostMapping("/create-list")
    public String createProductList() {
        productService.createProductsList();
        return "created";
    }
}