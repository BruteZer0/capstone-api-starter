package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Category;
import org.yearup.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(String name, String description)
    {
        // get all categories
        List<Category> categories = categoryRepository.findAll();
        if (name != null) {
            categories = categories.stream()
                    .filter(c -> c.getDescription() != null
                            && c.getDescription().toLowerCase().contains(description.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return categories;
    }

    public Category getById(int categoryId)
    {
        // get category by id
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public Category create(Category category)
    {
        // create a new category
        category.setCategoryId(0);
        return categoryRepository.save(category);
    }

    public Category update(int categoryId, Category category)
    {
        // update category and return the updated category
        Category existing = categoryRepository.findById(categoryId).orElseThrow();
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
        return categoryRepository.save(existing);
    }

    public void delete(int categoryId)
    {
        categoryRepository.deleteById(categoryId);
    }
}
