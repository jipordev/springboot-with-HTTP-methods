package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@RequestBody ProductCreateRequest request){
        System.out.println("Request" + request);
        productService.createNewProduct(request);
    }

    @DeleteMapping("/{id}")
    Boolean deleteProductById(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }

    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid, ProductEditRequest request){
        productService.updateProductByUuid(uuid,request);
    }

    @GetMapping()
    Map<String,Object> findProduct(@RequestParam(required = false, defaultValue = "") String name,
                                   @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return Map.of(
                "data", productService.findProducts(name, status)
        );
    }
    @GetMapping("/{id}")
    Map<String, Object> findProductById(@PathVariable Integer id) {
        return Map.of(
                "message","This is one product",
                "data",productService.findProductById(id)
        );
    }
    @GetMapping("/uuid/{uuid}")
    Map<String, Object> findProductByUuid(@PathVariable String uuid) {
        return Map.of(
                "message", "This is one product by uuid",
                "data",productService.findProductByUuid(uuid)
        );
    }





}
