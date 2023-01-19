package com.example.recipe33.servises;

import com.example.recipe33.model.RecipeModel;

public interface RecipeServise {
    public long addReсipe(RecipeModel receipe);

    public RecipeModel getRecipe(Long recipeId);

    void getAllRecipes();

    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew);

    public boolean deleteRecipe(Long id);
}
