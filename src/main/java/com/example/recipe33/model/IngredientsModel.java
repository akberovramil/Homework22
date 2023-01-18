package com.example.recipe33.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class IngredientsModel {
    @NonNull
    private String name;
    @NonNull
    private int numberOfIngredients;
    @NonNull
    private String amount;


}
