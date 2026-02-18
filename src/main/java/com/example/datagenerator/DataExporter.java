package com.example.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DataExporter {

    public static void main(String[] args) throws Exception {

        List<Product> products = ProductGenerator.generateProducts(50000);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(new File("src/main/resources/products.json"), products);

        System.out.println("Generated 50,000 products.");
    }
}
