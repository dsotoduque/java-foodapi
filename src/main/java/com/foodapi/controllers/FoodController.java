package com.foodapi.controllers;


import com.foodapi.models.responses.GenericResponse;
import com.foodapi.models.responses.RecipeCreateResponse;
import com.foodapi.service.FoodService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller("/food")
@ExecuteOn(TaskExecutors.BLOCKING)
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @Post(value = "/ingest/{query}", produces = MediaType.APPLICATION_JSON)
    public RecipeCreateResponse ingestRecipe(String query) throws IOException, ExecutionException, InterruptedException {
        return foodService.ingestRecipeRecord(query);
    }

    @Post(value = "/update/{recipeId}", produces = MediaType.APPLICATION_JSON)
    public GenericResponse updateRecipesDatabase(Long recipeId) throws IOException, ExecutionException, InterruptedException {
        return foodService.updateDatabase(recipeId);
    }
}
