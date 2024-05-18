package com.foodapi.repository;

import com.foodapi.models.RecipeDetails;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;


@MongoRepository
public interface RecipeDetailRepository extends CrudRepository<RecipeDetails, Long> {
}
