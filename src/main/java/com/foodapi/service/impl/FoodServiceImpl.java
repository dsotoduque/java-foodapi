package com.foodapi.service.impl;

import com.foodapi.models.Recipe;
import com.foodapi.models.RecipeDetails;
import com.foodapi.models.RecipeResponse;
import com.foodapi.models.responses.GenericResponse;
import com.foodapi.models.responses.RecipeCreateResponse;
import com.foodapi.repository.RecipeDetailRepository;
import com.foodapi.repository.RecipeRepository;
import com.foodapi.service.FoodService;
import com.foodapi.utils.ServiceCallBuilder;
import jakarta.inject.Singleton;
import static com.foodapi.utils.Actions.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Singleton
public class FoodServiceImpl implements FoodService {

    private final ServiceCallBuilder serviceCallBuilder;
    private final RecipeRepository recipeRepository;
    private final RecipeDetailRepository recipeDetailRepository;

    public FoodServiceImpl(ServiceCallBuilder serviceCallBuilder, RecipeRepository recipeRepository, RecipeDetailRepository recipeDetailRepository) {
        this.serviceCallBuilder = serviceCallBuilder;
        this.recipeRepository = recipeRepository;
        this.recipeDetailRepository = recipeDetailRepository;
    }


    @Override
    public RecipeCreateResponse ingestRecipeRecord(String query) throws IOException, ExecutionException, InterruptedException {
        RecipeResponse recipes = serviceCallBuilder.retrieveRecipes(query).get();
        boolean isPersisted = saveRecipeIntoDatabase(recipes);
        if (isPersisted) {
            return new RecipeCreateResponse(OK_STATUS_CODE.getIntegerAction(),
                    RECIPE_SUCCESS.getAction(),
                    recipes.getNumber());

        } else {
           return new RecipeCreateResponse(NOT_FOUND_RECIPE.getIntegerAction(),
                   RECIPE_ERROR.getAction(),
                   0);
        }
    }

    @Override
    public GenericResponse updateDatabase(Long recipeId) {
        RecipeDetails recipeDetail = null;
        try {
            recipeDetail = serviceCallBuilder.updateRecipesDatabase(recipeId).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (recipeDetailRepository.existsById(recipeId)) {
            recipeDetail = recipeDetailRepository.update(recipeDetail);
        } else {
            recipeDetail = recipeDetailRepository.save(recipeDetail);
        }
        GenericResponse genericResponse;
        if (recipeDetail != null) {
            genericResponse = new GenericResponse(OK_STATUS_CODE.getIntegerAction(),
                    RECIPE_DETAIL_SUCCESS.getAction());
        } else {
            genericResponse = new GenericResponse(NOT_FOUND_RECIPE.getIntegerAction(),
                    RECIPE_DETAIL_ERROR.getAction());
        }

        return genericResponse;
    }

    private boolean saveRecipeIntoDatabase(RecipeResponse recipes) {
        boolean response = false;
        for(Recipe recipe: recipes.getResults()){
            Recipe res = recipeRepository.save(recipe);
            response = res != null;
        }
        return response;
    }
}
