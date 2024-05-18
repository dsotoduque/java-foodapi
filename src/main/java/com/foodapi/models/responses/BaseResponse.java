package com.foodapi.models.responses;

public class BaseResponse {

    private Integer statusCode;

    private String baseResponseMessage;

    public BaseResponse(Integer statusCode, String baseResponseMessage) {
        this.statusCode = statusCode;
        this.baseResponseMessage = baseResponseMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBaseResponseMessage() {
        return baseResponseMessage;
    }

    public void setBaseResponseMessage(String baseResponseMessage) {
        this.baseResponseMessage = baseResponseMessage;
    }
}
