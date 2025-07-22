package com.mertalptekin.springbatchintro.product;

import com.mertalptekin.springbatchintro.model.Product;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Product verilerini okuyacağımız bir reader tanımı yaptık

@Component
public class ProductItemReader implements ItemReader<Product> {
    // cursor mantığı var.
    Iterator<Product> iterator;

    public ProductItemReader() {
        var p1 = new Product("P1",10.0,10);
        var p2 = new Product("P2",20.0,20);
        var p3 = new Product("P3",22.0,20);
        var p4 = new Product("P4",25.0,20);
        // chunksize(2)
        List<Product> products = List.of(p1,p2,p3,p4);
        iterator =  products.iterator();
    }

    // Read -> Processor -> Writer

    @Override
    public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        // bir sonraki kayıt varsa
        if (iterator.hasNext()) {
            return iterator.next(); // bir sonraki kaydı döndür.
        } else {
            return null;
        }

    }
}
