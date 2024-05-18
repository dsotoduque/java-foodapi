package com.foodapi.repository;

import com.foodapi.models.Recipe;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}