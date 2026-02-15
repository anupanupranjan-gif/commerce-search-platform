package com.example.datagenerator;

import java.util.Random;

public class ProductNameGenerator {

    private static final String[] ELECTRONICS_BASE = {
            "iPhone 15", "Galaxy S23", "MacBook Pro", "OLED TV", "Gaming Laptop"
    };

    public static String generateName(String category, String brand, Random random) {
        if (category.equals("Electronics")) {
            String base = ELECTRONICS_BASE[random.nextInt(ELECTRONICS_BASE.length)];
            return brand + " " + base + variantSuffix(random);
        }

        return brand + " Premium " + category + " " + (random.nextInt(100) + 1);
    }

    private static String variantSuffix(Random random) {
        String[] variants = {"", " Pro", " Max", " Plus", " Ultra", " 2024 Edition"};
        return variants[random.nextInt(variants.length)];
    }
}
