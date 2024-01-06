package com.devlach.classroom.categories.gateway;

import com.devlach.classroom.categories.dto.CategoryBasicDTO;
import com.devlach.classroom.categories.dto.CategoryDTO;
import com.devlach.classroom.categories.dto.CreateUpdateCategoryDTO;
import com.devlach.classroom.categories.service.CategoryService;
import com.devlach.classroom.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryGateway {

    private final CategoryService categoryService;

    public CategoryGateway(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category findById(Integer id) {
        return categoryService.findByIdOrThrow(id);
    }

    public List<CategoryDTO> treeCategories(boolean onlyRoot) {
        return categoryService.findAll().stream()
                .filter(category -> !onlyRoot || category.getParent() == null)
                .map(this::mapRecursiveDTO)
                .toList();
    }


    public List<CategoryBasicDTO> listBasicCategories() {
        return categoryService.findAll()
                .stream()
                .map(category -> new CategoryBasicDTO(
                        category.getId(),
                        category.getName()
                )).toList();
    }

    public CategoryDTO create(CreateUpdateCategoryDTO createCategoryDTO) {
        var category =  categoryService.create(createCategoryDTO);
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getParent() != null ? new CategoryDTO(
                        category.getParent().getId(),
                        category.getParent().getName(),
                        category.getParent().getDescription(),
                        null,
                        null
                ) : null,
                null
        );
    }

    public CategoryDTO update(Integer id, CreateUpdateCategoryDTO updateCategoryDTO) {
        return mapRecursiveDTO(categoryService.update(id, updateCategoryDTO));
    }

    public void delete(Integer id) {
        categoryService.delete(id);
    }

    private CategoryDTO mapRecursiveDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getParent() != null ? new CategoryDTO(
                        category.getParent().getId(),
                        category.getParent().getName(),
                        category.getParent().getDescription(),
                        null,
                        null
                ) : null,
                category.getSubcategories().stream()
                        .map(this::mapRecursiveDTO)
                        .toList()
        );
    }

}
