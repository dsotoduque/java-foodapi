package com.foodapi.models.responses;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class RecipeCreateResponse extends BaseResponse {
    private Integer totalResults;

    public RecipeCreateResponse(
             Integer statusCode,
            String baseResponseMessage,
            Integer totalResults) {
        super(statusCode, baseResponseMessage);
        this.totalResults = totalResults;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
