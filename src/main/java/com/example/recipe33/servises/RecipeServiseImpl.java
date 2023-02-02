package com.example.recipe33.servises;

import com.example.recipe33.exceptions.ExceptionApp;
import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public long addReсipe(RecipeModel receipe) throws ExceptionApp {
        if (!receipesMap.containsValue(receipe)) {
            receipesMap.put(recipeId, receipe);
            saveToFile();
            return recipeId++;
        } else {
            throw new ExceptionApp("Такой рецепт уже есть");
        }

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
    public RecipeModel editRecipe(Long id, RecipeModel recipeModelNew) throws ExceptionApp {
        if (receipesMap.containsKey(id)) {
            receipesMap.put(recipeId, recipeModelNew);
            saveToFile();
            return receipesMap.get(id);
        } else {
            throw new ExceptionApp("Такого рецепта нет");
        }
    }

    @Override
    public boolean deleteRecipe(Long id) throws ExceptionApp {
        if (receipesMap.containsKey(id)) {
            var removed = receipesMap.remove(id);
            return removed != null;
        } else {
            throw new ExceptionApp("Такого рецепта нет");
        }

    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(receipesMap);
            filesServise.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createTextRecipe() throws IOException {
        receipesMap.getOrDefault(recipeId, null);
        Path textRecipies = filesServise.createTempFile("recipes_txt");
        try (Writer writer = Files.newBufferedWriter(textRecipies, StandardCharsets.UTF_8)) {
            for (RecipeModel recipe : receipesMap.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder steps = new StringBuilder();
                for (IngredientsModel ingredient : recipe.getIngredients()) {
                    ingredients.append(ingredient).append(", \n");
                }
                for (String step : recipe.getSteps()) {
                    steps.append("\n").append(step);
                }
                writer.append(recipe.getName()).append("\n").append("Время приготовления: ")
                        .append(String.valueOf(recipe.getCookingTime())).append(" минут").append("\n")
                        .append("Необходимые ингредиенты:\n")
                        .append(ingredients.toString()).append(" Инструкция: ")
                        .append(steps.toString());
                writer.append("\n\n");
            }

        }
        return textRecipies;
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

