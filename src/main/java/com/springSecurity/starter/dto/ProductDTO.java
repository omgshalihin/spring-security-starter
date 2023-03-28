package com.springSecurity.starter.dto;

import lombok.Builder;

@Builder
public record ProductDTO(
        int productId,
        String name,
        int qty,
        double price
) {

}
