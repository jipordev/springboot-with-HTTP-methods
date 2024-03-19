package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import co.istad.springwebmvc.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> productList;
    public ProductServiceImpl(){
        productList = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("imax");
        p1.setPrice(1300.0);
        p1.setQty(2);
        p1.setImportedDate(LocalDateTime.now());
        p1.setStatus(true);
        Product p2 = new Product();
        p2.setId(2);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("imac");
        p2.setPrice(1600.0);
        p2.setQty(2);
        p2.setImportedDate(LocalDateTime.now());
        p2.setStatus(true);
        Product p3 = new Product();
        p3.setId(3);
        p3.setUuid(UUID.randomUUID().toString());
        p3.setName("imac");
        p3.setPrice(1600.0);
        p3.setQty(3);
        p3.setImportedDate(LocalDateTime.now());
        p3.setStatus(true);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
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

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setId(productList.size() + 1);
        newProduct.setUuid(UUID.randomUUID().toString());
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
    public boolean deleteProductById(Integer id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }
}
