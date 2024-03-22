package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
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
        System.out.println("Request" + request);
        productService.createNewProduct(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }


    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid,@RequestBody ProductEditRequest request){
        productService.updateProductByUuid(uuid,request);
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
    Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "data",productService.findProductById(id)
        );
    }

    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "data",productService.findProductByUuid(uuid)
        );
    }





}
