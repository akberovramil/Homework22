package com.example.recipe33.controllers;


import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.model.RecipeModel;
import com.example.recipe33.servises.IngredientsServise;
import com.example.recipe33.servises.IngredientsServiseImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    public IngredientsServiseImpl ingredientsServise;

    public IngredientsController(IngredientsServiseImpl ingredientsServise) {
        this.ingredientsServise = ingredientsServise;
    }

    @PostMapping
    public ResponseEntity<Long> addIngredient(@RequestBody IngredientsModel ingredient) {
        long id = ingredientsServise.addIngredients(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientsModel> getIngredient(@PathVariable Long id) {
        IngredientsModel ingredientsModel = ingredientsServise.getIngredients(id);
        if (ingredientsModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientsModel);
    }

    @GetMapping
    public ResponseEntity<IngredientsServiseImpl> getAllIngredients() {
        ingredientsServise.getAllRecipes();
        return ResponseEntity.ok(ingredientsServise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientsModel> editIngredient(@PathVariable Long id, @RequestBody IngredientsModel ingredient) {

        IngredientsModel ingredientsModel = ingredientsServise.editIngredient(id, ingredient);
        if (ingredientsModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientsModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        if (ingredientsServise.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
