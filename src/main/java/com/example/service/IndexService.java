package com.example.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch._types.analysis.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IndexService {

    private final ElasticsearchClient client;

    public IndexService(ElasticsearchClient client) {
        this.client = client;
    }

    public void createProductIndex() throws IOException {
        boolean exists = client.indices().exists(e -> e.index("products")).value();
        if (exists) {
            System.out.println("Index 'products' already exists.");
            return;
        }

        CreateIndexResponse response = client.indices().create(c -> c
                .index("products")
                .settings(s -> s
                        .analysis(a -> a
                                // Define a synonym filter
                                .filter("my_synonyms", f -> f
                                        .definition(tf -> tf
                                                .synonym(syn -> syn
                                                        .synonyms("tv, television, smart tv",
                                                                "phone, smartphone, mobile",
                                                                "laptop, notebook, ultrabook",
                                                                "sneakers, shoes")
                                                )
                                        )
                                )
                                // Custom analyzer using the synonym filter
                                .analyzer("custom_synonym", an -> an
                                        .custom(ca -> ca
                                                .tokenizer("standard")
                                                .filter("lowercase", "my_synonyms")
                                        )
                                )
                        )
                )
                .mappings(m -> m
                        .properties("name", p -> p.text(t -> t.analyzer("custom_synonym")))
                        .properties("description", p -> p.text(t -> t))
                        .properties("brand", p -> p.keyword(k -> k))
                        .properties("category", p -> p.keyword(k -> k))
                        .properties("price", p -> p.double_(d -> d))
                        .properties("rating", p -> p.double_(d -> d))
                        .properties("reviewCount", p -> p.integer(i -> i))
                        .properties("salesCount", p -> p.integer(i -> i))
                        .properties("inStock", p -> p.boolean_(b -> b))
                        .properties("createdDate", p -> p.date(d -> d))
                        .properties("tags", p -> p.keyword(k -> k))
                )
        );

        System.out.println("Index created: " + response.acknowledged());
    }
}

