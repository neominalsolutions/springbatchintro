package com.mertalptekin.springbatchintro.product;

import com.mertalptekin.springbatchintro.model.Product;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ProductItemWriter  implements ItemWriter<Product> {
    @Override
    public void write(Chunk<? extends Product> chunk) throws Exception {

        // chunka gönderilen toplu verileri yazdırdığımız kısım
        System.out.println("Writing product..." + chunk.size());
        System.out.println("Writing product...");
    }
}
