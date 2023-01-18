package com.example.recipe33.servises;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiseImpl implements RecipeServise {
    Map<Long, RecipeServise> receipesMap = new HashMap<>();
    private static Long recipeId = 1L;
    @Override
    public RecipeServise addReсipe(RecipeServise receipe) {
        receipesMap.put(recipeId, receipe);
        return receipe;
    }

    @Override
    public RecipeServise getRecipe(Long recipeId) {
        return receipesMap.get(recipeId);
    }
}
