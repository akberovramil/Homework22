package com.example.recipe33.servises;

import com.example.recipe33.exceptions.ExceptionApp;
import com.example.recipe33.model.IngredientsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiseImpl implements IngredientsServise {

    @Value("${name.of.ingredients.data.file}")
    private String dataFileName;

    private final FilesServise filesServise;

    private Map<Long, IngredientsModel> ingredientsMap = new HashMap<>();
    private static Long ingredientId = 1L;

    public IngredientsServiseImpl(FilesServise filesServise) {
        this.filesServise = filesServise;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }


    @Override
    public long addIngredients(IngredientsModel ingredients) throws ExceptionApp {
        if (!ingredientsMap.containsValue(ingredients)) {
            ingredientsMap.put(ingredientId, ingredients);
            saveToFile();
            return ingredientId++;
        } else {
            throw new ExceptionApp("Такой ингредиент уже есть");
        }

    }

    @Override
    public IngredientsModel getIngredients(Long id) throws ExceptionApp {

        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new ExceptionApp("Такого ингредиента нет");
        }
    }

    @Override
    public Collection<IngredientsModel> getAllIngredients() {
        return ingredientsMap.values();
    }

    @Override
    public IngredientsModel editIngredient(Long id, IngredientsModel ingredientsModelNew) throws ExceptionApp {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredientsModelNew);
            saveToFile();
            return ingredientsMap.get(id);
        } else {
            throw new ExceptionApp("Ингредиент нельзя редактировать, так как его нет");
        }

    }
@Override
    public boolean deleteIngredient(Long id) throws ExceptionApp {

    if (ingredientsMap.containsKey(id)) {
        var removed = ingredientsMap.remove(id);
        return removed != null;
    } else {
        throw new ExceptionApp("Ингредиент удалить нельзя, так как его нет");
    }

    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesServise.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        File file = new File(dataFileName);
        if (file.exists()) {
            String json = filesServise.readFromFile();
            try {
                ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, IngredientsModel>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
