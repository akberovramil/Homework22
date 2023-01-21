package com.example.recipe33.servises;

import com.example.recipe33.model.IngredientsModel;

import java.util.Collection;

public interface IngredientsServise {
    public long addIngredients(IngredientsModel ingredients);

    public IngredientsModel getIngredients(Long id);

    public Collection<IngredientsModel> getAllIngredients();

    public IngredientsModel editIngredient(Long id, IngredientsModel ingredientsModelNew);

    public boolean deleteIngredient(Long id);

}
