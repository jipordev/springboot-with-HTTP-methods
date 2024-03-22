package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

}
