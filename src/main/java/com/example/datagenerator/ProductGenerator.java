package com.example.datagenerator;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.*;

public class ProductGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static List<Product> generateProducts(int count) {

        List<Product> products = new ArrayList<>();

        List<String> categories = CategoryData.getCategories();

        for (int i = 0; i < count; i++) {

            String category = categories.get(random.nextInt(categories.size()));
            List<String> brands = CategoryData.getBrands(category);
            String brand = brands.get(random.nextInt(brands.size()));

            String name = ProductNameGenerator.generateName(category, brand, random);
            name = MisspellingUtil.maybeMisspell(name, random);

            Product product = new Product(
                    UUID.randomUUID().toString(),
                    name,
                    faker.lorem().sentence(),
                    brand,
                    category,
                    10 + (5000 * random.nextDouble()),
                    2 + (3 * random.nextDouble()),
                    random.nextInt(5000),
                    random.nextInt(20000),
                    random.nextDouble() > 0.1,
                    random.nextInt(50),
                    LocalDate.now().minusDays(random.nextInt(365)),
                    List.of(category.toLowerCase(), brand.toLowerCase())
            );

            products.add(product);
        }

        return products;
    }
}
