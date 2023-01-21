package com.example.recipe33.servises;

import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public IngredientsModel getIngredients(Long id) {
            return ingredientsMap.get(id);
    }

    @Override
    public Collection<IngredientsModel> getAllIngredients() {
        return ingredientsMap.values();
    }

    @Override
    public IngredientsModel editIngredient(Long id, IngredientsModel ingredientsModelNew) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.put(id, ingredientsModelNew);
        }
        return null;
    }
@Override
    public boolean deleteIngredient(Long id) {
    var removed = ingredientsMap.remove(id);
    return removed != null;
    }
}
