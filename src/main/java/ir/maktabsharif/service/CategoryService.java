package ir.maktabsharif.service;

import ir.maktabsharif.repository.category.CategoryRepositoryFactory;
import ir.maktabsharif.model.Category;
import ir.maktabsharif.repository.Repository;

import java.util.Optional;

public class CategoryService {

    private final Repository<Category> categoryRepository;

    public CategoryService() {
        CategoryRepositoryFactory factory = new CategoryRepositoryFactory();

        this.categoryRepository = factory.createRepository();
    }

    // CREATE
    public Long save(Category category) {
        return categoryRepository.save(category);
    }

    // READ
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    // UPDATE
    public boolean update(Long id ,Category category) {
        return categoryRepository.update(id , category);
    }

    // DELETE
    public boolean delete(Long id) {
        return categoryRepository.delete(id);
    }
}