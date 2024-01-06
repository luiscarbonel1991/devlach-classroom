package com.devlach.classroom.categories.persistence.repository;

import com.devlach.classroom.entity.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
}
