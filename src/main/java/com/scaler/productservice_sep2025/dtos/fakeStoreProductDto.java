package com.scaler.productservice_sep2025.dtos;

import com.scaler.productservice_sep2025.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class fakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private Category category;
    private String image_url;
}
