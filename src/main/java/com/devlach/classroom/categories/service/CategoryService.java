package com.devlach.classroom.categories.service;

import com.devlach.classroom.api.exception.BadRequestException;
import com.devlach.classroom.api.exception.NotFoundException;
import com.devlach.classroom.categories.dto.CreateUpdateCategoryDTO;
import com.devlach.classroom.categories.persistence.repository.CategoryRepository;
import com.devlach.classroom.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category create(CreateUpdateCategoryDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.name());
        category.setDescription(categoryCreateDTO.description());
        setParent(categoryCreateDTO.parentId(), category);
        return categoryRepository.save(category);
    }

    public Category update(Integer id, CreateUpdateCategoryDTO categoryUpdateDTO) {
        Category categoryToUpdate = findById(id).orElseThrow(() -> NotFoundException.categoryId(id));

        if (categoryUpdateDTO.name() != null) {
            categoryToUpdate.setName(categoryUpdateDTO.name());
        }
        if (categoryUpdateDTO.description() != null) {
            categoryToUpdate.setDescription(categoryUpdateDTO.description());
        }

        setParent(categoryUpdateDTO.parentId(), categoryToUpdate);

        return categoryRepository.save(categoryToUpdate);
    }

    public void delete(Integer id) {
        if(isDefaultCategory(id)) throw BadRequestException.categoryCanNotBeDeleted(id);
        Category categoryToDelete = findById(id).orElseThrow(() -> NotFoundException.categoryId(id));
        categoryRepository.deleteById(categoryToDelete.getId());
    }

    public Category findByIdOrThrow(Integer id) {
        return findById(id).orElseThrow(() -> NotFoundException.categoryId(id));
    }

    private Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    private void setParent(Integer parentId, Category categoryNew) {
        if (parentId != null) {
            Category parentCategory = findById(parentId).orElseThrow(() -> NotFoundException.parentCategoryId(parentId));
            categoryNew.setParent(parentCategory);
        } else {
            categoryNew.setParent(null);
        }
    }

    private static boolean isDefaultCategory(Integer id) {
        return Category.DEFAULT_CATEGORY_ID.equals(id);
    }

}
