package com.example.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import com.example.datagenerator.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ElasticsearchClient client;

    public SearchService(ElasticsearchClient client) {
        this.client = client;
    }

    public List<Product> searchProducts(String query, String brandFilter, String categoryFilter, Double minPrice, Double maxPrice) throws IOException {

        Query keywordQuery = MultiMatchQuery.of(m -> m
                .fields("name", "description")
                .query(query)
        )._toQuery();

        // Build filter queries
        Query brandQuery = brandFilter != null ? TermQuery.of(t -> t.field("brand").value(brandFilter))._toQuery() : null;
        Query categoryQuery = categoryFilter != null ? TermQuery.of(t -> t.field("category").value(categoryFilter))._toQuery() : null;
      /*  Query priceQuery = (minPrice != null || maxPrice != null) ?
                RangeQuery.of(r -> r
                        .field("price") // The field to query
                        .gte(minPrice) // Greater than or equal to 50
                        .lte(maxPrice) // Less than or equal to 100
                )._toQuery(): null;*/



        // Combine filters
        BoolQuery.Builder boolQuery = new BoolQuery.Builder().must(keywordQuery);
        if (brandQuery != null) boolQuery.filter(brandQuery);
        if (categoryQuery != null) boolQuery.filter(categoryQuery);
        //if (priceQuery != null) boolQuery.filter(priceQuery);

        SearchRequest request = SearchRequest.of(s -> s
                .index("products")
                .query(q -> q.bool(boolQuery.build()))
                .size(50) // top 50 results
        );

        SearchResponse<Product> response = client.search(request, Product.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }
}
