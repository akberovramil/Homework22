package com.example.recipe33.servises;

import com.example.recipe33.exceptions.ExceptionProject;
import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

public interface RecipeServise {
    public long addReсipe(RecipeModel receipe) throws ExceptionProject;

    public RecipeModel getRecipe(Long recipeId);

    public boolean deleteRecipe(Long id) throws ExceptionProject;

    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew) throws ExceptionProject;


    public Collection<RecipeModel> getAllRecipe();

    Path createTextRecipe() throws IOException;
}
