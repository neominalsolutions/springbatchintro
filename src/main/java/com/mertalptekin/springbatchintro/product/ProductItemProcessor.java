package com.mertalptekin.springbatchintro.product;

import com.mertalptekin.springbatchintro.model.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

// <T1:Read,T2:Write>

@Component
public class ProductItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {

        item.setPrice(item.getPrice() * 0.1); // %10

        return item;
    }
}
