## Project Overview

The Commerce Search Platform is a Spring Boot-based microservice application designed to manage product data, index it into Elasticsearch, and provide a search API for products.  
This repository contains all source code, configuration, and Docker setup for the Spring Boot application.  

The project is structured to support deployment in both local environments and Kubernetes clusters, with observability built in for monitoring application performance and search reliability.

# Commerce Search Platform

This repository contains the Spring Boot application for the Commerce Search Platform.  
It handles product data generation, indexing into Elasticsearch, and search services.

## Project Structure

.
├── Dockerfile
├── pom.xml
├── docker-compose.yml
├── products.json
├── src
│ ├── main
│ │ ├── java # Application source code
│ │ └── resources # Application properties and resources
│ └── test/java # Unit and integration tests
└── target # Maven build output


## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker (for local container builds)

### Build & Run Locally

```bash
# Build the JAR
mvn clean package

# Run locally
java -jar target/advanced-search-demo-0.0.1-SNAPSHOT.jar
Docker
# Build Docker image
docker build -t advanced-search-app:latest .

# Run with Docker
docker run -p 8080:8080 advanced-search-app:latest