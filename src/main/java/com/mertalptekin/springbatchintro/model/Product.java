package com.mertalptekin.springbatchintro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Verileri farklı Steplerle paylaşmak için ExecutionContext içerisine atacağız. bu durumda nesnenin ya jsonString olması lazım yada serializable olması lazım

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable {

    @JsonProperty("ProductName")
    private String name;

    @JsonProperty("UnitPrice")
    private Double price;

    @JsonProperty("UnitsInStock")
    public Integer stock;

}
