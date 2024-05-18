package com.foodapi.utils;


public enum Actions {
    RECIPE_SUCCESS("Recipe success"),
    RECIPE_DETAIL_SUCCESS("Recipe detail success"),
    RECIPE_ERROR("Recipe error"),
    RECIPE_DETAIL_ERROR("Recipe detail error"),
    OK_STATUS_CODE("200"),
    NOT_FOUND_RECIPE("404"),
    SEARCH("search?"),
    INFO("information?");

    private final String action;

    Actions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public Integer getIntegerAction(){
        if (tryParse(action)){
            return Integer.parseInt(action);
        } else {
            throw new NumberFormatException("Not Able to Parse");
        }
    }

    private boolean tryParse(String action) {
        try {
            Integer.parseInt(action);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

