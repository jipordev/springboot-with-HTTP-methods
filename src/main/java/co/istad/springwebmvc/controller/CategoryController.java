package co.istad.springwebmvc.controller;


import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the categories",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Categories not found",
                content = @Content)
    })
    @GetMapping
    ResponseEntity<?> findCategories(){
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", categoryService.findCategories()
                )
        );
    }

    @GetMapping("/name/{name}")
    ResponseEntity<?> findCategoryByName(@PathVariable String name) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data",categoryService.findCategoryByName(name)
                )
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findCategoryById(@PathVariable Integer id){
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", categoryService.findCategoryById(id)
                )
        );
    }

    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.createNewCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    CategoryResponse editCategoryById(@PathVariable Integer id,@Valid @RequestBody CategoryRequest request) {
        return categoryService.editCategoryById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategoryById(@PathVariable Integer id) {
         categoryService.deleteCategoryById(id);
    }

}
