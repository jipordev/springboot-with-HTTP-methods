package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){

        productService.createNewProduct(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductByUuid(id);
    }

    @PutMapping("/{id}")
    ProductResponse editProductById(@PathVariable Integer id, @Valid @RequestBody ProductEditRequest request){
        return productService.editProductById(id, request);
    }

    @GetMapping
    ResponseEntity<?> findProduct(@RequestParam(required = false, defaultValue = "") String name,
                               @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", productService.findProducts(name, status)
                )
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<?> findProductById(@PathVariable Integer id) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", productService.findProductById(id)
                )
        );
    }

    @GetMapping("/uuid/{uuid}")
    ResponseEntity<?> findProductByUuid(@PathVariable String uuid) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data",productService.findProductByUuid(uuid)
                )
        );
    }





}
