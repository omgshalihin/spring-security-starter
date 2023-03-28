package com.springSecurity.starter.service;

import com.springSecurity.starter.dto.ProductDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<ProductDTO> productDTOList = null;

    @PostConstruct
    public void loadProductsFromDB() {
        productDTOList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> ProductDTO.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }


    public List<ProductDTO> getProducts() {
        return productDTOList;
    }

    public ProductDTO getProduct(int id) {
        return productDTOList.stream()
                .filter(productDTO -> productDTO.productId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }
}
