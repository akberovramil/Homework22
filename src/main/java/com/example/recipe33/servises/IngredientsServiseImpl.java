package com.example.recipe33.servises;

import com.example.recipe33.model.IngredientsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public long addIngredients(IngredientsModel ingredients) {
        ingredientsMap.put(ingredientId, ingredients);
        saveToFile();
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
            ingredientsMap.put(id, ingredientsModelNew);
            saveToFile();
            return ingredientsMap.get(id);
        }
        return null;
    }
@Override
    public boolean deleteIngredient(Long id) {
    var removed = ingredientsMap.remove(id);
    return removed != null;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesServise.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        File file = new File(dataFileName);
        if (file.exists()) {
            String json = filesServise.readFromFile(dataFileName);
            try {
                ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, IngredientsModel>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
