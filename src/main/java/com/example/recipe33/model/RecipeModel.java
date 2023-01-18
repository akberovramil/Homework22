package com.example.recipe33.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class RecipeModel {
    @NonNull
    private String name;
    @NonNull
    private int cookingTime;
    @NonNull
    private List<IngredientsModel> ingredients = new ArrayList<>();
    @NonNull
    private List<String> steps = new ArrayList<>();


}
