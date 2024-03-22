package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.ProductCreateRequest;
import co.istad.springwebmvc.dto.ProductEditRequest;
import co.istad.springwebmvc.dto.ProductResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findProducts(String name, Boolean status);
    ProductResponse findProductById(Integer id);

    ProductResponse findProductByUuid(String uuid);
    void createNewProduct(ProductCreateRequest request);
    ProductResponse editProductById(Integer id, ProductEditRequest request);
    void deleteProductByUuid(String uuid);
}
