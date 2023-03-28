package com.springSecurity.starter.dto;

import lombok.Builder;

@Builder
public record Product(
        int productId,
        String name,
        int qty,
        double price
) {

}
