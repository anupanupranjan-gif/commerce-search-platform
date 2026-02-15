package com.example.controller;

import com.example.datagenerator.Product;
import com.example.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<Product> search(
            @RequestParam String query,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) throws IOException {
        return searchService.searchProducts(query, brand, category, minPrice, maxPrice);
    }
}
