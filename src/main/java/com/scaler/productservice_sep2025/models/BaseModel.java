package com.scaler.productservice_sep2025.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private Long id;
    private Date Created_At;
    private Date lastUpdate;
    private State state;
}
