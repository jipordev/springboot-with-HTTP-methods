package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.dto.ProductResponse;
import co.istad.springwebmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    ProductResponse findByUuid(String uuid);

}
