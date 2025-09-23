package com.scaler.productservice_sep2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private double price;
    private CategoryDto categoryDto;
}
