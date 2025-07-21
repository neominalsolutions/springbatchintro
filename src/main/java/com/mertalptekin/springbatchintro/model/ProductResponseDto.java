package com.mertalptekin.springbatchintro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDto {
    private List<Product> value;
}
