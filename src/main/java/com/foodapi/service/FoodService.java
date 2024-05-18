package com.foodapi.service;

import com.foodapi.models.responses.GenericResponse;
import com.foodapi.models.responses.RecipeCreateResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface FoodService {
    RecipeCreateResponse ingestRecipeRecord(String query) throws IOException, ExecutionException, InterruptedException;
    GenericResponse updateDatabase(Long recipeId) throws IOException, ExecutionException, InterruptedException;
}
