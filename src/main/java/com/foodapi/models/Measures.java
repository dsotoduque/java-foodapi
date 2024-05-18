package com.foodapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.micronaut.core.annotation.Introspected;


@Introspected
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measures {

    private Double amount;
    private String unitShort;
    private String unitLong;

    public Measures(Double amount, String unitShort, String unitLong) {
        this.amount = amount;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
    }

    public Measures() {

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }
}
