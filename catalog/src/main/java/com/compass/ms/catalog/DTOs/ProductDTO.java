package com.compass.ms.catalog.DTOs;

import com.compass.ms.catalog.models.Variation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private boolean active;
    private List<Variation> variations = new ArrayList<>();
}
