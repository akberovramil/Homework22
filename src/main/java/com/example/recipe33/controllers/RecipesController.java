package com.example.recipe33.controllers;

import com.example.recipe33.model.RecipeModel;
import com.example.recipe33.servises.RecipeServise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receipes")
public class RecipesController {
    public RecipeServise recipeServise;

    public RecipesController(RecipeServise recipeServise) {
        this.recipeServise = recipeServise;
    }

    @GetMapping("/add")
    public RecipeServise putReceipes(@RequestParam RecipeServise receipe) {
        return recipeServise.addReсipe(receipe);
    }

    @GetMapping("/get")
    public RecipeServise showReceipe(@RequestParam Long id) {
        return recipeServise.getRecipe(id);
    }
}
