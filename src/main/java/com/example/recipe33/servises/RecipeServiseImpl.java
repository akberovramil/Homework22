package com.example.recipe33.servises;

import com.example.recipe33.model.RecipeModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiseImpl implements RecipeServise {
    Map<Long, RecipeModel> receipesMap = new HashMap<>();
    private static Long recipeId = 1L;
    @Override
    public long addReсipe(RecipeModel receipe) {
        Map<Long, RecipeModel> recipes = receipesMap;
        recipes.put(recipeId, receipe);
        receipesMap.put(recipeId, receipe);
        return recipeId++;
    }

    @Override
    public RecipeModel getRecipe(Long id) {
        for (RecipeModel value : receipesMap.values()) {
            RecipeModel recipeModel = receipesMap.get(id);
            if (recipeModel != null) {
                return recipeModel;
            }
        }
        return null;
    }

    @Override
    public void getAllRecipes() {
        receipesMap = new HashMap<>();
    }

    @Override
    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew) {
        for (Map.Entry<Long, RecipeModel> recipeModelEntry : receipesMap.entrySet()) {
            RecipeModel recipeModel1 = receipesMap.get(id);
            if (recipeModel1 != null) {
                recipeModelEntry.setValue(recipeModel1);
                receipesMap.put(id, recipeModelNew);
                return recipeModelNew;
            }
        }
        return null;
    }



    @Override
    public boolean deleteRecipe(Long id) {
        for (Map.Entry<Long, RecipeModel> recipeModelEntry : receipesMap.entrySet()) {
            RecipeModel recipeModel = receipesMap.get(id);
            if (recipeModel != null) {
                receipesMap.remove(id, recipeModel);
                return true;
            }
            }
        return false;
    }
}
