package com.scaler.productservice_sep2025.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String description;
    private String image_url;
    private double price;
    private Category category;

    //bussiness Specific
    public Product(){
        this.setCreated_At(new Date());
        this.setLastUpdate(new Date());
        this.setState(State.Active);
    }
}
