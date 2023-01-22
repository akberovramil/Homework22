package com.example.recipe33.controllers;

import com.example.recipe33.model.RecipeModel;
import com.example.recipe33.servises.RecipeServise;
import com.example.recipe33.servises.RecipeServiseImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipes")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipesController {
    private final RecipeServiseImpl recipeServise;

    public RecipesController(RecipeServiseImpl recipeServise) {
        this.recipeServise = recipeServise;
    }

    @PostMapping()
    @Operation(
            summary = "Добавить рецепт"
    )
    public ResponseEntity<Long> addReceipes(@RequestBody RecipeModel receipe) {
        long id = recipeServise.addReсipe(receipe);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Найти рецепт по id"
    )
    public ResponseEntity<RecipeModel> getRecipeById(@PathVariable Long id) {
        RecipeModel recipeModel = recipeServise.getRecipe(id);
        if (recipeModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeModel);
    }

    @GetMapping
    @Operation(
            summary = "Список всех рецептов"
    )
    public ResponseEntity<RecipeServiseImpl> getAllRecipes() {
        recipeServise.getAllRecipe();
        return ResponseEntity.ok(recipeServise);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактировать рецепт"
    )
    public ResponseEntity<RecipeModel> editRecipe(@PathVariable Long id, @RequestBody RecipeModel recipeModel) {

        RecipeModel recipe = recipeServise.editRecipe(id, recipeModel);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeModel);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить рецепт"
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        if (recipeServise.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
