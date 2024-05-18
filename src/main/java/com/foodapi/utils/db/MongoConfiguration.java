package com.foodapi.utils.db;


import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Factory
public class MongoConfiguration {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017/foodapi");
    }
}

