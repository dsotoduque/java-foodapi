package com.foodapi.models.responses;

public class GenericResponse extends BaseResponse{
    public GenericResponse(Integer statusCode, String baseResponseMessage) {
        super(statusCode, baseResponseMessage);
    }
}
