package com.example.recipe33.controllers;


import com.example.recipe33.exceptions.ExceptionApp;
import com.example.recipe33.model.IngredientsModel;
import com.example.recipe33.servises.FilesServise;
import com.example.recipe33.servises.IngredientsServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы с ингредиентами")
public class IngredientsController {

    private FilesServise filesServise;
    private final IngredientsServise ingredientsServise;

    public IngredientsController(FilesServise filesServise, IngredientsServise ingredientsServise) {
        this.filesServise = filesServise;
        this.ingredientsServise = ingredientsServise;
    }

    @PostMapping
    public ResponseEntity<Long> addIngredient(@RequestBody IngredientsModel ingredient) throws ExceptionApp {
        long id = ingredientsServise.addIngredients(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Найти ингредиент по id"
    )
    public ResponseEntity<IngredientsModel> getIngredient(@PathVariable Long id) throws ExceptionApp {
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
    public ResponseEntity<IngredientsModel> editIngredient(@PathVariable Long id, @RequestBody IngredientsModel ingredient) throws ExceptionApp {

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
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) throws ExceptionApp {
        if (ingredientsServise.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipes(@RequestParam MultipartFile file) {
        filesServise.cleanDataFile();
        File dataFile = filesServise.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
