package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final List<Product> productList;
    public ProductServiceImpl(){
        productList = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(shortUuid(UUID.randomUUID().toString()));
        p1.setName("iPhone XS Max");
        p1.setPrice(1300.0);
        p1.setQty(2);
        p1.setImportedDate(LocalDateTime.now());
        p1.setStatus(true);
        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(shortUuid(UUID.randomUUID().toString()));
        p2.setName("iPhone 11 Pro Max");
        p2.setPrice(1600.0);
        p2.setQty(2);
        p2.setImportedDate(LocalDateTime.now());
        p2.setStatus(true);
        Product p3 = new Product();
        p3.setId(3);
        p3.setUuid(shortUuid(UUID.randomUUID().toString()));
        p3.setName("iPhone 12 Pro Max");
        p3.setPrice(1600.0);
        p3.setQty(3);
        p3.setImportedDate(LocalDateTime.now());
        p3.setStatus(true);
        Product p4 = new Product();
        p4.setId(4);
        p4.setUuid(shortUuid(UUID.randomUUID().toString()));
        p4.setName("iPhone 13 Pro Max");
        p4.setPrice(1600.0);
        p4.setQty(3);
        p4.setImportedDate(LocalDateTime.now());
        p4.setStatus(true);
        Product p5 = new Product();
        p5.setId(5);
        p5.setUuid(shortUuid(UUID.randomUUID().toString()));
        p5.setName("iPhone 14 Pro Max");
        p5.setPrice(1600.0);
        p5.setQty(3);
        p5.setImportedDate(LocalDateTime.now());
        p5.setStatus(true);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
    }

    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
        return productList.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()) && product.getStatus().equals(status))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst().orElseThrow();
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        return productList.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).findFirst().orElseThrow();
    }
    static String shortUuid(String uuid){
        return uuid.substring(0,8);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setId(productList.size() + 1);
        newProduct.setUuid(shortUuid(UUID.randomUUID().toString()));
        newProduct.setImportedDate(LocalDateTime.now());
        newProduct.setStatus(true);
        productList.add(newProduct);
    }

    @Override
    public void updateProductByUuid(String uuid ,ProductEditRequest request) {

        // Check product uuid
        long count = productList.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .peek(oldProduct -> {
                    oldProduct.setName(request.name());
                    oldProduct.setPrice(request.price());
                }).count();
        System.out.println("Affected row = " + count);

    }

    @Override
    public void deleteProductByUuid(String uuid) {
        productList.removeIf(product -> product.getUuid().equals(uuid));
        log.info("Affected row: {}",1);
    }
}
