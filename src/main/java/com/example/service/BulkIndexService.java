package com.example.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import com.example.datagenerator.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BulkIndexService {

    private final ElasticsearchClient client;
    private final ObjectMapper mapper;

    public BulkIndexService(ElasticsearchClient client) {
        this.client = client;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule()); // Handles LocalDate
    }

    public void bulkIndexProducts(String filePath) throws IOException {
        List<Product> products = List.of(mapper.readValue(new File(filePath), Product[].class));

        BulkRequest.Builder bulk = new BulkRequest.Builder();

        for (Product p : products) {
            bulk.operations(op -> op
                    .index(idx -> idx
                            .index("products")
                            .id(p.getId())
                            .document(p)
                    )
            );
        }

        BulkResponse response = client.bulk(bulk.build());

        System.out.println("Indexed " + response.items().size() + " products");
    }
}
