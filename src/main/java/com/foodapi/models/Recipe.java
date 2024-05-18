package com.foodapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;


@Introspected
@MappedEntity("recipes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    @Id
    private Long id;

    private String title;
    private String image;
    private Integer readyInMinutes;
    private String sourceUrl;
    private Integer servings;

    // Constructores, getters y setters

    public Recipe() {
    }

    public Recipe(String title, String image, Integer readyInMinutes, String source, Integer servings) {
        this.title = title;
        this.image = image;
        this.readyInMinutes = readyInMinutes;
        this.sourceUrl = source;
        this.servings = servings;
    }

    // Getters y Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
