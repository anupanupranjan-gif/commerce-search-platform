package com.example.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;

@Configuration
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUri;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();

        JacksonJsonpMapper mapper = new JacksonJsonpMapper(objectMapper);

        URI uri = URI.create(elasticsearchUri);

        RestClient restClient = RestClient.builder(
                new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme())
        ).build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, mapper);

        return new ElasticsearchClient(transport);
    }
}
