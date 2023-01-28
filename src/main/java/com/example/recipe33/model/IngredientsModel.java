package com.example.recipe33.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class IngredientsModel {
    @NonNull
    private String name;
    @Positive
    private int numberOfIngredients;
    @NonNull
    private String amount;


}
