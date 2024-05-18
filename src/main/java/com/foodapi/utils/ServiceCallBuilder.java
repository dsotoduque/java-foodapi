package com.foodapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapi.models.RecipeResponse;
import io.micronaut.http.*;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Singleton;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.foodapi.models.RecipeDetails;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@ExecuteOn(TaskExecutors.IO)
@Singleton
public class  ServiceCallBuilder {

    private final String basePath = "https://api.spoonacular.com/recipes/";
    private String apiKey = "1c7dc2af5cd04a49a0db7d8c2f6c4f58";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);


    public ServiceCallBuilder(@Client(basePath) HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }



    public CompletableFuture<RecipeResponse> retrieveRecipes(String query) {
        String url = basePath + Actions.SEARCH.getAction() + "query=" + query + "&apiKey=" + apiKey;

        HttpRequest<?> request = HttpRequest.GET(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        return CompletableFuture.supplyAsync(() -> {
            try {
                String response = httpClient.toBlocking().retrieve(request, String.class);
                return mapToRecipesResponse(response);
            } catch (HttpClientResponseException e) {
                if (e.getStatus() == HttpStatus.NOT_FOUND) {
                    throw new RuntimeException("Resource not found: " + url, e);
                } else {
                    throw new RuntimeException("Unexpected error occurred", e);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error processing response", e);
            }
        }, executorService);
    }

    public CompletableFuture<RecipeDetails> updateRecipesDatabase(Long recipeId) {
        String url = basePath
                + recipeId + "/"
                + Actions.INFO.getAction()
                + "apiKey=" + apiKey;

        HttpRequest<?> request = HttpRequest.GET(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        return CompletableFuture.supplyAsync(() -> {
            String response = httpClient.toBlocking().retrieve(request, String.class);
            try {
                return mapToRecipeDetail(response);
            } catch (IOException e) {
                throw new RuntimeException("Error processing response", e);
            }
        }, executorService);
    }

    private RecipeResponse mapToRecipesResponse(String responseBody) throws IOException {
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);

        return objectMapper.readValue(responseBody, RecipeResponse.class);
    }

    private RecipeDetails mapToRecipeDetail(String responseBody) throws IOException {
        SimpleModule module = new SimpleModule();
        objectMapper.registerModule(module);

        return objectMapper.readValue(responseBody, RecipeDetails.class);
    }


}
