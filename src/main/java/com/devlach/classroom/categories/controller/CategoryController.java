package com.devlach.classroom.categories.controller;

import com.devlach.classroom.categories.dto.CategoryBasicDTO;
import com.devlach.classroom.categories.dto.CategoryDTO;
import com.devlach.classroom.categories.dto.CreateUpdateCategoryDTO;
import com.devlach.classroom.categories.gateway.CategoryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryGateway categoryGateway;

    public CategoryController(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @GetMapping("/tree")
    public ResponseEntity<List<CategoryDTO>> treeCategories(@RequestParam(required = false, defaultValue = "false") boolean onlyRoot) {
        return ResponseEntity.ok(
                categoryGateway.treeCategories(onlyRoot)
        );
    }


    @GetMapping
    public ResponseEntity<List<CategoryBasicDTO>> listBasicCategories() {
        return ResponseEntity.ok(
                categoryGateway.listBasicCategories()
        );
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CreateUpdateCategoryDTO categoryCreateDTO, UriComponentsBuilder ucb) {
            var category = categoryGateway.create(categoryCreateDTO);
            var  uri = ucb.path("/api/v1/categories/{id}").buildAndExpand(category.id()).toUri();
            return ResponseEntity.created(uri).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @RequestBody CreateUpdateCategoryDTO categoryUpdateDTO) {
        return ResponseEntity.ok(
                categoryGateway.update(id, categoryUpdateDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryGateway.delete(id);
        return ResponseEntity.noContent().build();
    }

}
