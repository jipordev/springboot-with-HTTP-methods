package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.repository.ProductRepository;
import co.istad.springwebmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name) && product.getStatus().equals(status))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).toList();
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product has not been found"
                )
        );
        return new ProductResponse(product.getUuid(),
                product.getName(),
                product.getPrice(),
                product.getQty());
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        return productRepository.findByUuid(uuid);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {

        Product product = new Product();
        product.setUuid(shortUuid(UUID.randomUUID().toString()));
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);

    }
    static String shortUuid(String uuid){
        return uuid.substring(0,8);
    }

    @Override
    public ProductResponse editProductById(Integer id, ProductEditRequest request) {

        Product product = productRepository.findById(id).orElseThrow(()->
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found"
            )
        );
        product.setName(request.name());
        product.setPrice(request.price());
        productRepository.save(product);

        return this.findProductById(id);
    }

    @Override
    public void deleteProductByUuid(Integer id) {
        if (!productRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found"
            );
        }

        productRepository.deleteById(id);

    }
}
