package com.example.recipe33.controllers;

import com.example.recipe33.model.RecipeModel;
import com.example.recipe33.servises.RecipeServise;
import com.example.recipe33.servises.RecipeServiseImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipes")
public class RecipesController {
    private final RecipeServiseImpl recipeServise;

    public RecipesController(RecipeServiseImpl recipeServise) {
        this.recipeServise = recipeServise;
    }

    @PostMapping()
    public ResponseEntity<Long> addReceipes(@RequestBody RecipeModel receipe) {
        long id = recipeServise.addReсipe(receipe);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeModel> getRecipeById(@PathVariable Long id) {
        RecipeModel recipeModel = recipeServise.getRecipe(id);
        if (recipeModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeModel);
    }

    @GetMapping
    public ResponseEntity<RecipeServiseImpl> getAllRecipes() {
        recipeServise.getAllRecipes();
        return ResponseEntity.ok(recipeServise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeModel> editRecipe(@PathVariable Long id, @RequestBody RecipeModel recipeModel) {

        RecipeModel recipe = recipeServise.editRecipe(id, recipeModel);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        if (recipeServise.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
