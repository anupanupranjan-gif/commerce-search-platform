package com.example;

import com.example.service.BulkIndexService;
import com.example.service.IndexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchDemoApplication implements CommandLineRunner {

    private final IndexService indexService;
    private final BulkIndexService bulkService;

    public SearchDemoApplication(IndexService indexService, BulkIndexService bulkService) {
        this.indexService = indexService;
        this.bulkService = bulkService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SearchDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        indexService.createProductIndex();
        bulkService.bulkIndexProducts("products.json"); // path to your dataset
    }
}
