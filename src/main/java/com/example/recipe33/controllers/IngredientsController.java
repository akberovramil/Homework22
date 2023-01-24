package com.example.recipe33.controllers;


import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;
import com.example.recipe33.servises.IngredientsServise;
import com.example.recipe33.servises.IngredientsServiseImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы с ингредиентами")
public class IngredientsController {
    public IngredientsServise ingredientsServise;

    public IngredientsController(IngredientsServise ingredientsServise) {
        this.ingredientsServise = ingredientsServise;
    }

    @PostMapping
    public ResponseEntity<Long> addIngredient(@RequestBody IngredientsModel ingredient) {
        long id = ingredientsServise.addIngredients(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Найти ингредиент по id"
    )
    public ResponseEntity<IngredientsModel> getIngredient(@PathVariable Long id) {
        IngredientsModel ingredientsModel = ingredientsServise.getIngredients(id);
        if (ingredientsModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientsModel);
    }

    @GetMapping
    @Operation(
            summary = "Список всех ингредиентов"
    )

    public ResponseEntity<Collection> getAllIngredients() {
        return ResponseEntity.ok(ingredientsServise.getAllIngredients());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактировать ингредиент по id"
    )
    public ResponseEntity<IngredientsModel> editIngredient(@PathVariable Long id, @RequestBody IngredientsModel ingredient) {

        IngredientsModel ingredientsModel = ingredientsServise.editIngredient(id, ingredient);
        if (ingredientsModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientsModel);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить ингредиент по id"
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        if (ingredientsServise.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
