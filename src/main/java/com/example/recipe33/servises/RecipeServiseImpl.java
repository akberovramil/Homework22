package com.example.recipe33.servises;

import com.example.recipe33.model.RecipeModel;
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
public class RecipeServiseImpl implements RecipeServise {

    @Value("${name.of.recipes.data.file}")
    private String dataFileName;

    private final FilesServise filesServise;
    private Map<Long, RecipeModel> receipesMap = new HashMap<>();
    private static Long recipeId = 1L;

    public RecipeServiseImpl(FilesServise filesServise) {
        this.filesServise = filesServise;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }


    @Override
    public long addReсipe(RecipeModel receipe) {
        receipesMap.put(recipeId, receipe);
        saveToFile();
        return recipeId++;
    }

    @Override
    public RecipeModel getRecipe(Long id) {
        return receipesMap.get(id);
    }



    @Override
    public Collection<RecipeModel> getAllRecipe() {
        return receipesMap.values();
    }

    @Override
    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew) {
        if (receipesMap.containsKey(id)) {
            receipesMap.put(recipeId, recipeModelNew);
            saveToFile();
            return receipesMap.get(id);
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Long id) {
        var removed = receipesMap.remove(id);
        return removed != null;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(receipesMap);
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
                receipesMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long, RecipeModel>>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

