package com.example.apidesign.dto;

import com.example.apidesign.model.Continent;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CountryDTO {
    private Long id;
    private String name;
    private String continent;
}
