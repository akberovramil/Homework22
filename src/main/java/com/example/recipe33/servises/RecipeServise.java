package com.example.recipe33.servises;

import com.example.recipe33.exceptions.ExceptionApp;
import com.example.recipe33.model.RecipeModel;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface RecipeServise {
    public long addReсipe(RecipeModel receipe) throws ExceptionApp;

    public RecipeModel getRecipe(Long recipeId);

    public boolean deleteRecipe(Long id) throws ExceptionApp;

    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew) throws ExceptionApp;


    public Collection<RecipeModel> getAllRecipe();

    Path createTextRecipe() throws IOException;
}
