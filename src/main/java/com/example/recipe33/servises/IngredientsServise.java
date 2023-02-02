package com.example.recipe33.servises;

import com.example.recipe33.exceptions.ExceptionApp;
import com.example.recipe33.model.IngredientsModel;

import java.util.Collection;

public interface IngredientsServise {
    public long addIngredients(IngredientsModel ingredients) throws ExceptionApp;

    public IngredientsModel getIngredients(Long id) throws ExceptionApp;

    public Collection<IngredientsModel> getAllIngredients();

    public IngredientsModel editIngredient(Long id, IngredientsModel ingredientsModelNew) throws ExceptionApp;

    public boolean deleteIngredient(Long id) throws ExceptionApp;

}
