package co.istad.springwebmvc.repository;

import co.istad.springwebmvc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Derived query method - Auto Generated query
    boolean existsByName(String name);

}
