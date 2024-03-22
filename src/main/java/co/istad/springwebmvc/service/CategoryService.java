package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.CategoryRequest;
import co.istad.springwebmvc.dto.CategoryResponse;
import co.istad.springwebmvc.model.Category;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findCategories();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse findCategoryByName(String name);
    void createNewCategory(CategoryRequest categoryRequest);
    CategoryResponse editCategoryById(Integer id, CategoryRequest categoryRequest);
    void deleteCategoryById(Integer id);
}
