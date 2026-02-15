package com.example.datagenerator;

import java.util.*;

public class CategoryData {

    public static final Map<String, List<String>> CATEGORY_BRANDS = Map.of(
            "Electronics", List.of("Apple", "Samsung", "Sony", "LG", "Dell", "HP"),
            "Shoes", List.of("Nike", "Adidas", "Puma", "Reebok"),
            "Books", List.of("Penguin", "HarperCollins", "OReilly", "McGrawHill"),
            "Home", List.of("Philips", "Ikea", "KitchenAid", "Whirlpool"),
            "Fashion", List.of("Zara", "H&M", "Levis", "Calvin Klein")
    );

    public static List<String> getCategories() {
        return new ArrayList<>(CATEGORY_BRANDS.keySet());
    }

    public static List<String> getBrands(String category) {
        return CATEGORY_BRANDS.get(category);
    }
}
