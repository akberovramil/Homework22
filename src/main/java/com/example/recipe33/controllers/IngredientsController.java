package com.example.recipe33.controllers;


import com.example.recipe33.servises.IngredientsServise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    public IngredientsServise ingredientsServise;

    public IngredientsController(IngredientsServise ingredientsServise) {
        this.ingredientsServise = ingredientsServise;
    }

    @GetMapping("/add")
    public IngredientsServise putIngredient(@RequestParam IngredientsServise ingredient) {
        return ingredientsServise.addIngredients(ingredient);
    }

    @GetMapping("/get")
    public IngredientsServise showIngredient(@RequestParam Long id) {
        return ingredientsServise.getIngredients(id);
    }



}
