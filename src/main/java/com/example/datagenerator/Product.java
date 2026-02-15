package com.example.datagenerator;

import java.time.LocalDate;
import java.util.List;

public class Product {

    private String id;
    private String name;
    private String description;
    private String brand;
    private String category;
    private double price;
    private double rating;
    private int reviewCount;
    private int salesCount;
    private boolean inStock;
    private int discountPercent;
    private LocalDate createdDate;
    private List<String> tags;

    // Constructor
   public Product() {
    }
    public Product(String id, String name, String description, String brand,
                   String category, double price, double rating,
                   int reviewCount, int salesCount, boolean inStock,
                   int discountPercent, LocalDate createdDate, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.salesCount = salesCount;
        this.inStock = inStock;
        this.discountPercent = discountPercent;
        this.createdDate = createdDate;
        this.tags = tags;
    }


    // Getters only (for clean immutability)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getBrand() { return brand; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public int getReviewCount() { return reviewCount; }
    public int getSalesCount() { return salesCount; }
    public boolean isInStock() { return inStock; }
    public int getDiscountPercent() { return discountPercent; }
    public LocalDate getCreatedDate() { return createdDate; }
    public List<String> getTags() { return tags; }
}

