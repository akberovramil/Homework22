package com.example.recipe33.servises;

import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

public interface RecipeServise {
    public long addReсipe(RecipeModel receipe);

    public RecipeModel getRecipe(Long recipeId);

    public boolean deleteRecipe(Long id);

    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew);


    public Collection<RecipeModel> getAllRecipe();
}
