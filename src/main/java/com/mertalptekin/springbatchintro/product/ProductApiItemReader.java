package com.mertalptekin.springbatchintro.product;

import com.mertalptekin.springbatchintro.model.Product;
import com.mertalptekin.springbatchintro.model.ProductResponseDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

// https://services.odata.org/northwind/northwind.svc/Products?$skip=2&$top=2&$format=json

@Component
public class ProductApiItemReader implements ItemReader<Product> {
    @Autowired
    private RestTemplate restTemplate;
    Iterator<Product> iterator;
    List<Product> data;

    @Override
    public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(data == null){
            System.out.println("veri çekiliyor");
            fetchData();
        }
        if(iterator.hasNext()) {
            return  iterator.next(); // bir sonraki kaydı döndür.
        } else {
            return  null;
        }
    }

    private  void  fetchData() {
        String apiUrl = "https://services.odata.org/northwind/northwind.svc/Products?$format=json";
        ResponseEntity<ProductResponseDto> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                ProductResponseDto.class);

        data = response.getBody().getValue();
        iterator = data.iterator();
    }
}
