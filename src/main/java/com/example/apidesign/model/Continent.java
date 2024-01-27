package com.example.apidesign.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Continent {
    @Id
    private int id;
    private String name;
}
