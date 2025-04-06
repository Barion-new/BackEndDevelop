package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Category;
import me.barion.capstoneprojectbarion.dto.CategoryDto;
import me.barion.capstoneprojectbarion.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
    }

    @Transactional
    public Category createCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsById(categoryDto.getCategoryId())) {
            throw new IllegalArgumentException("이미 존재하는 ID: " + categoryDto.getCategoryId());
        }

        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());

        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Integer id, CategoryDto updatedDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));

        existingCategory.setCategoryName(updatedDto.getCategoryName());
        return categoryRepository.save(existingCategory);
    }

    @Transactional
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 카테고리가 없습니다: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
