package com.example.recipe33.servises;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiseImpl implements IngredientsServise {

    private Map<Long, IngredientsServise> ingredientsMap = new HashMap<>();
    private static Long ingredientId = 1L;
    @Override
    public IngredientsServise addIngredients(IngredientsServise ingredients) {
        ingredientsMap.put(ingredientId, ingredients);
        ingredientId++;
        return ingredients;
    }

    @Override
    public IngredientsServise getIngredients(Long ingredientId) {
        return ingredientsMap.get(ingredientId);
    }
}
