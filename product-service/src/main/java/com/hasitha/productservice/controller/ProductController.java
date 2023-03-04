package com.hasitha.productservice.controller;

import com.hasitha.productservice.dto.ProductRequestDTO;
import com.hasitha.productservice.dto.ProductResponseDTO;
import com.hasitha.productservice.model.Product;
import com.hasitha.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO saveProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.saveProduct(productRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAllProducts(){
        return productService.getAllProducts();
    }
}
