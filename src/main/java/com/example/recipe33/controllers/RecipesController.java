package com.example.recipe33.controllers;

import com.example.recipe33.exceptions.ExceptionProject;
import com.example.recipe33.model.RecipeModel;
import com.example.recipe33.servises.FilesServise;
import com.example.recipe33.servises.RecipeServise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/receipes")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipesController {
    private final RecipeServise recipeServise;
    private FilesServise filesServise;

    public RecipesController(RecipeServise recipeServise, FilesServise filesServise) {
        this.recipeServise = recipeServise;
        this.filesServise = filesServise;
    }

    @PostMapping()
    @Operation(
            summary = "Добавить рецепт"
    )
    public ResponseEntity<Long> addReceipes(@RequestBody RecipeModel receipe) throws ExceptionProject {
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
    public ResponseEntity<Collection> getAllRecipes() {
        return ResponseEntity.ok(recipeServise.getAllRecipe());
    }

    @GetMapping("/export")
    @Operation(
            summary = "Скачать список всех рецептов"
    )
    public ResponseEntity<InputStreamResource> downloadRecipes() throws FileNotFoundException {
        File file = filesServise.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).contentLength(file.length()).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"").body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipes(@RequestParam MultipartFile file) {
        filesServise.cleanDataFile();
        File dataFile = filesServise.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактировать рецепт"
    )
    public ResponseEntity<RecipeModel> editRecipe(@PathVariable Long id, @RequestBody RecipeModel recipeModel) throws ExceptionProject {

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
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) throws ExceptionProject {
        if (recipeServise.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/exportTxt")
    public ResponseEntity<Object> recipesExport() {
        try {
            Path txt = recipeServise.createTextRecipe();
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(txt.toFile()));
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(txt))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + "recipes_txt")
                    .body(inputStreamResource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
