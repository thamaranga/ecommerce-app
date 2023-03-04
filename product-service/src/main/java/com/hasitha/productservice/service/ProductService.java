package com.hasitha.productservice.service;

import com.hasitha.productservice.dto.ProductRequestDTO;
import com.hasitha.productservice.dto.ProductResponseDTO;
import com.hasitha.productservice.model.Product;
import com.hasitha.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO=null;
        Product product=Product.builder().name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice()).build();

        Product savedProduct=productRepository.save(product);
        if(savedProduct!=null){
            productResponseDTO=ProductResponseDTO.builder().id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .description(savedProduct.getDescription())
                    .price(savedProduct.getPrice())
                    .build();
        }
        return productResponseDTO;
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productResponseDTOList=null;
        List<Product> allProducts = productRepository.findAll();
        productResponseDTOList=allProducts.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
        return  productResponseDTOList;
    }

    private ProductResponseDTO mapToProductResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
