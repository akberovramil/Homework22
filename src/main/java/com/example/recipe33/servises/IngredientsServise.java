package com.example.recipe33.servises;

import com.example.recipe33.exceptions.ExceptionProject;
import com.example.recipe33.model.IngredientsModel;

import java.util.Collection;

public interface IngredientsServise {
    public long addIngredients(IngredientsModel ingredients) throws ExceptionProject;

    public IngredientsModel getIngredients(Long id) throws ExceptionProject;

    public Collection<IngredientsModel> getAllIngredients();

    public IngredientsModel editIngredient(Long id, IngredientsModel ingredientsModelNew) throws ExceptionProject;

    public boolean deleteIngredient(Long id) throws ExceptionProject;

}
