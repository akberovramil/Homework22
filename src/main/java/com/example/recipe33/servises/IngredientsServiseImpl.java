package com.example.recipe33.servises;

import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiseImpl implements IngredientsServise {

    private Map<Long, IngredientsModel> ingredientsMap = new HashMap<>();
    private static Long ingredientId = 1L;
    @Override
    public long addIngredients(IngredientsModel ingredients) {
        Map<Long, IngredientsModel> ingredientsMapNew = ingredientsMap;
        ingredientsMapNew.put(ingredientId, ingredients);
        ingredientsMap.put(ingredientId, ingredients);
        return ingredientId++;
    }

    @Override
    public IngredientsModel getIngredients(Long id)
    {
        for (IngredientsModel value : ingredientsMap.values()) {
            IngredientsModel ingredientsModel = ingredientsMap.get(id);
            if (ingredientsModel != null) {
                return ingredientsModel;
            }
        }
        return null;
    }

    @Override
    public void getAllRecipes() {
        ingredientsMap = new HashMap<>();
    }

    @Override
    public IngredientsModel editIngredient(Long id, IngredientsModel ingredientsModelNew) {
        for (Map.Entry<Long, IngredientsModel> ingredientsModelEntry : ingredientsMap.entrySet()) {
            IngredientsModel ingredientsModel1 = ingredientsMap.get(id);
            if (ingredientsModel1 != null) {
                ingredientsModelEntry.setValue(ingredientsModel1);
                ingredientsMap.put(id, ingredientsModelNew);
                return ingredientsModelNew;
            }
        }
        return null;
    }
@Override
    public boolean deleteIngredient(Long id) {
        for (Map.Entry<Long, IngredientsModel> ingredientsModelEntry : ingredientsMap.entrySet()) {
            IngredientsModel ingredientsModel = ingredientsMap.get(id);
            if (ingredientsModel != null) {
                ingredientsMap.remove(id, ingredientsModel);
                return true;
            }
        }
        return false;
    }
}
