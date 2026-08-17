package ir.maktabsharif.repository.category;


import ir.maktabsharif.model.Category;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.repository.RepositoryFactory;

public class CategoryRepositoryFactory extends RepositoryFactory<Category> {

    @Override
    public Repository<Category> createRepository() {
        return new CategoryRepository();
    }
}